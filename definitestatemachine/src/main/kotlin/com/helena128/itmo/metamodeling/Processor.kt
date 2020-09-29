package com.helena128.metamodeling

data class DSMTransition(val startState: String, val inputSymbol: Char, val resultState: String)

fun ArrayList<DSMTransition>.findStartState(): DSMTransition? {
    this.sortBy { transition -> transition.startState }
    return this.getOrNull(0)
}

fun ArrayList<DSMTransition>.findLastState(): DSMTransition? {
    this.sortByDescending { transition -> transition.resultState }
    return this.getOrNull(0)
}

fun process(stateInput: ArrayList<DSMTransition>, characterSequence: String): Boolean {

    fun getTransition(currentState: String?, inputSymbol: Char): String? {
        return stateInput
            .filter{row -> row.startState.equals(currentState) && row.inputSymbol.equals(inputSymbol)}
            .take(1)
            .map { res -> res.resultState }
            .getOrNull(0)
    }

    val startState = stateInput.findStartState()
    val endState = stateInput.findLastState()

    var currentState = startState?.startState

    for ((index, symbol) in characterSequence.withIndex()) {
        val nextState = getTransition(currentState, symbol)
        if (nextState.isNullOrEmpty()) {
            println("Couldn't find the next state, startState = $currentState, inputSymbol = $symbol")
            return false
        }
        println("Current state $currentState, inputSymbol = $symbol, nextState = $nextState")
        currentState = nextState
        if (index == characterSequence.length - 1) {
            return currentState.equals(endState?.resultState)
        }

    }
    return false;
}
