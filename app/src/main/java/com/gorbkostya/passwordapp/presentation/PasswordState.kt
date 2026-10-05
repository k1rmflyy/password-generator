package com.gorbkostya.passwordapp.presentation

import com.gorbkostya.passwordapp.domain.CharOption
import com.gorbkostya.passwordapp.domain.Strength

data class PasswordState(
    val length: Int = 16,
    val options: Set<CharOption> = CharOption.entries.toSet(),
    val password: String = "",
    val bits: Int = 0,
    val strength: Strength = Strength.WEAK,
    val isCopied: Boolean = false
)

sealed interface PasswordCommand {
    data class ChangeLength(val newLength: Int): PasswordCommand
    data class ToggleOption(val option: CharOption): PasswordCommand
    data object Regenerate: PasswordCommand
    data object Copied: PasswordCommand
}