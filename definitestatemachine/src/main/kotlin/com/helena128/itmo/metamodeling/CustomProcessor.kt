package com.helena128.itmo.metamodeling

import java.lang.IllegalArgumentException

// DSM has 6 states, 3 possible inputs - 'a', 'b', 'c'
fun isInputAcceptedByPreDefinedDSM(input: String): Boolean {
    return dft(inputSequence = input.toCharArray())
}

private tailrec fun dft(currentIndex: Int = 0, inputSequence: CharArray, currentState: Int = 1): Boolean {
    return if (currentIndex == inputSequence.size) (currentState == 6)
    else dft(currentIndex + 1, inputSequence, getNextState(currentState, inputSequence[currentIndex]));
}

private fun getNextState(currentState: Int, inputChar: Char): Int {
    when (currentState) {
        1 -> when (inputChar) {
            'a' -> return 2
            'b' -> return 3
            'c' -> return 4
        }
        2 -> when (inputChar) {
            'a' -> return 2
            'b' -> return 5
        }
        3 -> when (inputChar) {
            'b' -> return 3
            'c' -> return 6
        }
        4 -> when (inputChar) {
            'c' -> return 4
            'b' -> return 5
        }
        5 -> when (inputChar) {
            'c' -> return 6
        }
    }
    throw IllegalArgumentException("Unknown input character = $inputChar, state = $currentState")
}

