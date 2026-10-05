package com.gorbkostya.passwordapp.domain

import kotlin.math.log2
import kotlin.math.roundToInt

enum class Strength(val title: String, val segments: Int) {
    WEAK("Слабый", 1),
    MEDIUM("Средний", 2),
    STRONG("Надёжный", 3),
    VERY_STRONG("Очень надёжный", 4)
}


fun calculateBits(length: Int, options: Set<CharOption>): Int {
    val poolSize = options.sumOf { it.chars.length }
    return (log2(poolSize.toDouble()) * length).roundToInt()
}


fun strengthOf(bits: Int): Strength {
    return when {
        bits < 45 -> Strength.WEAK
        bits < 65 -> Strength.MEDIUM
        bits < 85 -> Strength.STRONG
        else -> Strength.VERY_STRONG
    }
}
