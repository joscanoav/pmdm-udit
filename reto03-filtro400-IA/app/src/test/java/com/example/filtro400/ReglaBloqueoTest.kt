package com.example.filtro400

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ReglaBloqueoTest {

    @Test fun bloquea400SinPrefijoPais() =
        assertTrue(ReglaBloqueo.debeBloquearse("400123456"))

    @Test fun bloquea400ConMas34() =
        assertTrue(ReglaBloqueo.debeBloquearse("+34400123456"))

    @Test fun bloquea400Con0034() =
        assertTrue(ReglaBloqueo.debeBloquearse("0034400123456"))

    @Test fun bloquea400ConEspacios() =
        assertTrue(ReglaBloqueo.debeBloquearse("+34 400 123 456"))

    @Test fun noBloqueaMovil() =
        assertFalse(ReglaBloqueo.debeBloquearse("612345678"))

    @Test fun noBloqueaFijo() =
        assertFalse(ReglaBloqueo.debeBloquearse("912345678"))

    @Test fun noBloqueaInternacionalQueContiene400() =
        assertFalse(ReglaBloqueo.debeBloquearse("+1400123456"))

    @Test fun noBloqueaNumeroVacio() =
        assertFalse(ReglaBloqueo.debeBloquearse(""))
}