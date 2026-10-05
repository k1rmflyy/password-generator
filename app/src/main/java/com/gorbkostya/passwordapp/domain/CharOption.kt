package com.gorbkostya.passwordapp.domain

enum class CharOption(val title: String, val chars: String, val sample: String) {
    UPPER("Заглавные", "ABCDEFGHJKLMNPQRSTUVWXYZ", "A-Z"),
    LOWER("Строчные", "abcdefghijkmnopqrstuvwxyz", "a-z"),
    DIGITS("Цифры", "23456789", "2-9"),
    SYMBOLS("Символы", "!@#$%^&*?-_+=", "!@#$%")
}