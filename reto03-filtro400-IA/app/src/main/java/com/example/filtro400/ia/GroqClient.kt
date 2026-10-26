package com.example.filtro400.ia

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

data class Msg(val role: String, val text: String) // role: "user" | "assistant"

/**
 * Cliente mínimo de Groq (API compatible con OpenAI).
 * ⚠️ API key embebida: válido SOLO para demo. En producción iría en un backend.
 */
object GroqClient {


    private const val API_KEY  = ""
    private const val URL_API = "https://api.groq.com/openai/v1/chat/completions"
    private const val MODELO = "openai/gpt-oss-20b"

    suspend fun responder(
        historial: List<Msg>,
        system: String = BaseConocimiento.PROMPT_LLAMANTE
    ): String = withContext(Dispatchers.IO) {
        val mensajes = JSONArray().put(JSONObject().put("role", "system").put("content", system))
        historial.forEach { mensajes.put(JSONObject().put("role", it.role).put("content", it.text)) }
        val cuerpo = JSONObject()
            .put("model", MODELO)
            .put("messages", mensajes)
            .put("temperature", 0.4)
            .put("max_tokens", 800)
            .put("reasoning_effort", "low")

        val con = (URL(URL_API).openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            connectTimeout = 10_000
            readTimeout = 20_000
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("Authorization", "Bearer $API_KEY")
        }
        con.outputStream.use { it.write(cuerpo.toString().toByteArray()) }
        val ok = con.responseCode in 200..299
        val texto = (if (ok) con.inputStream else con.errorStream).bufferedReader().readText()
        if (!ok) error("Groq ${con.responseCode}: $texto")
        JSONObject(texto).getJSONArray("choices").getJSONObject(0)
            .getJSONObject("message").getString("content").trim()
    }

    /** Resume la conversación llamante↔asistente en 2 líneas para el dueño. */
    suspend fun resumir(historial: List<Msg>): String {
        val transcripcion = historial.joinToString("\n") {
            (if (it.role == "user") "Llamante: " else "Asistente: ") + it.text
        }
        return responder(
            listOf(Msg("user", "Resume en 2 líneas: quién llamó, motivo y si es urgente.\n\n$transcripcion")),
            system = "Resumes llamadas telefónicas en español, de forma breve y clara."
        )
    }
}
