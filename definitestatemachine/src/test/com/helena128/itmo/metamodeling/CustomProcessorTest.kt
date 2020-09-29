package com.helena128.itmo.metamodeling

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertTrue

class CustomProcessorTest {

    @Test
    fun testAPath() {
        assertTrue { isInputAcceptedByPreDefinedDSM("aaabc") }
        assertTrue { isInputAcceptedByPreDefinedDSM("abc") }
    }

    @Test
    fun testBPath() {
        assertTrue { isInputAcceptedByPreDefinedDSM("bbc") }
        assertTrue { isInputAcceptedByPreDefinedDSM("bbbbbc") }
        assertTrue { isInputAcceptedByPreDefinedDSM("bc") }
    }

    @Test
    fun testCPath() {
        assertTrue { isInputAcceptedByPreDefinedDSM("ccbc") }
        assertTrue { isInputAcceptedByPreDefinedDSM("cbc") }
        assertTrue { isInputAcceptedByPreDefinedDSM("ccbc") }
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNegative() {
        isInputAcceptedByPreDefinedDSM("aba")
    }
}