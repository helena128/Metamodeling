package com.helena128.metamodeling

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProcessorTest {

    val dsm = arrayListOf<DSMTransition>(
        DSMTransition("s0", 'a',  "s1"),
        DSMTransition("s0", 'b',  "s0"),
        DSMTransition("s1", 'a',  "s2"),
        DSMTransition("s2", 'a',  "s2"),
        DSMTransition("s2", 'b',  "s2")
    )

    @Test
    fun positiveTest() {
        val inputString = "baaba"
        assertTrue { process(dsm, inputString) }
    }

    @Test
    fun negativeTest() {
        val inputString = "bab";
        assertFalse { process(dsm, inputString) }
    }
}