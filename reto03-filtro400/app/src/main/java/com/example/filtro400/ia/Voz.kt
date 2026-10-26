package com.example.filtro400.ia

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import java.util.Locale

/** Voz de salida (TTS) y de entrada (STT) con las APIs nativas de Android. */
class Voz(private val ctx: Context) {
    var sonido by mutableStateOf(true)
    var escuchando by mutableStateOf(false)
    private var listo = false
    private var pendiente: String? = null
    private var tts: TextToSpeech? = null
    private var reconocedor: SpeechRecognizer? = null

    init {
        tts = TextToSpeech(ctx) { estado ->
            if (estado == TextToSpeech.SUCCESS) {
                tts?.language = Locale("es", "ES")
                listo = true
                pendiente?.let { hablar(it) }
                pendiente = null
            }
        }
    }

    fun hablar(texto: String) {
        if (!sonido) return
        if (listo) tts?.speak(texto, TextToSpeech.QUEUE_FLUSH, null, "voz") else pendiente = texto
    }

    fun callar() { tts?.stop() }

    fun escuchar(alTexto: (String) -> Unit) {
        if (!SpeechRecognizer.isRecognitionAvailable(ctx)) return
        callar()
        reconocedor?.destroy()
        val sr = SpeechRecognizer.createSpeechRecognizer(ctx)
        reconocedor = sr
        sr.setRecognitionListener(object : RecognitionListener {
            override fun onResults(b: Bundle?) {
                escuchando = false
                b?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.let(alTexto)
            }
            override fun onError(e: Int) { escuchando = false }
            override fun onReadyForSpeech(p: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(b: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(b: Bundle?) {}
            override fun onEvent(t: Int, b: Bundle?) {}
        })
        escuchando = true
        sr.startListening(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
        })
    }

    fun cerrar() { reconocedor?.destroy(); tts?.shutdown() }
}

@Composable
fun rememberVoz(): Voz {
    val app = LocalContext.current.applicationContext
    val voz = remember { Voz(app) }
    DisposableEffect(Unit) { onDispose { voz.cerrar() } }
    return voz
}

/** Devuelve una función que pide el permiso del micrófono (si hace falta) y empieza a escuchar. */
@Composable
fun rememberEscuchar(voz: Voz, alTexto: (String) -> Unit): () -> Unit {
    val ctx = LocalContext.current
    val permiso = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { ok ->
        if (ok) voz.escuchar(alTexto)
    }
    return {
        val concedido = ContextCompat.checkSelfPermission(ctx, Manifest.permission.RECORD_AUDIO) ==
            PackageManager.PERMISSION_GRANTED
        if (concedido) voz.escuchar(alTexto) else permiso.launch(Manifest.permission.RECORD_AUDIO)
    }
}
