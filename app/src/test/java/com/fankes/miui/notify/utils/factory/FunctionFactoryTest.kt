package com.fankes.miui.notify.utils.factory

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FunctionFactoryTest {

    @Test
    fun supportsHyperOs4() {
        assertTrue(isSupportedMiSystemVersion(isMios = true, miosVersion = "4.0", isMiui = true, miuiVersion = "816"))
    }

    @Test
    fun rejectsUnknownHyperOsVersion() {
        assertFalse(isSupportedMiSystemVersion(isMios = true, miosVersion = "5.0", isMiui = true, miuiVersion = "816"))
    }

    @Test
    fun keepsSupportedMiuiVersions() {
        assertTrue(isSupportedMiSystemVersion(isMios = false, miosVersion = "", isMiui = true, miuiVersion = "14"))
        assertFalse(isSupportedMiSystemVersion(isMios = false, miosVersion = "", isMiui = true, miuiVersion = "15"))
    }
}
