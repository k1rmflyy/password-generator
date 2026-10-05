package com.gorbkostya.passwordapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorbkostya.passwordapp.domain.PasswordGenerator
import com.gorbkostya.passwordapp.domain.calculateBits
import com.gorbkostya.passwordapp.domain.strengthOf
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class PasswordViewModel(private val generator: PasswordGenerator = PasswordGenerator()): ViewModel() {
    private val _state = MutableStateFlow(PasswordState())
    val state = _state.asStateFlow()

    private var copiedJob: Job? = null

    init {
        regenerate()
    }

    fun processCommand(command: PasswordCommand) {
        when(command) {
            is PasswordCommand.ChangeLength -> {
                val length = _state.value.length
                val newLength = command.newLength.coerceIn(6, 32)
                if (newLength ==  length) {
                    return
                }

                _state.update { it.copy(length = newLength) }
                regenerate()

            }
            PasswordCommand.Copied -> {
                _state.update { it.copy(isCopied = true) }
                copiedJob?.cancel()
                copiedJob = viewModelScope.launch {
                    delay(1600.milliseconds)
                    _state.update { it.copy(isCopied = false) }
                }

            }
            PasswordCommand.Regenerate -> {
                regenerate()
            }
            is PasswordCommand.ToggleOption -> {
                val options = _state.value.options
                val option = command.option
                if (option in options && options.size == 1) {
                    return
                }

                val newOptions = if (option in options) {
                    options - option
                } else {
                    options + option
                }

                _state.update { it.copy(options = newOptions) }
                regenerate()
            }
        }
    }

    private fun regenerate() {
        val length = _state.value.length
        val options = _state.value.options
        val password = generator.generate(length, options)
        val bits = calculateBits(length, options)
        val strength = strengthOf(bits)
        _state.update { it.copy(password = password, bits = bits, strength = strength) }
    }
}