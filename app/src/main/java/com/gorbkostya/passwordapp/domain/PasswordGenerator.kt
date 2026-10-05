package com.gorbkostya.passwordapp.domain

import java.security.SecureRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom


class PasswordGenerator(private val random: Random = SecureRandom().asKotlinRandom()) {
    fun generate(length: Int, options: Set<CharOption>): String {
        require(options.isNotEmpty()) { "Нужна хотя бы одна опция" }
        require(length >= options.size) { "Длина меньше числа опций" }

        val pool = options.joinToString("") { it.chars }
        val result = mutableListOf<Char>()

        for (option in options) {
            result.add(option.chars.random(random))
        }

        while (result.size < length) {
            result.add(pool.random(random))
        }

        result.shuffle(random)

        return result.joinToString("")
    }
}