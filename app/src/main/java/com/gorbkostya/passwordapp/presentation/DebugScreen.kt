package com.gorbkostya.passwordapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gorbkostya.passwordapp.domain.CharOption
import com.gorbkostya.passwordapp.ui.theme.LocalExtraColors
import com.gorbkostya.passwordapp.ui.theme.PasswordTextStyle

@Composable
fun DebugScreen(viewModel: PasswordViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = state.password
        )

        Text(
            text = state.length.toString()
        )

        Text(
            text = state.bits.toString()
        )

        Text(
            text = state.strength.title
        )

        Text(
            text = state.options.toString()
        )

        Text(
            text = state.isCopied.toString()
        )

        Button(onClick = {viewModel.processCommand(PasswordCommand.Regenerate)}) {
            Text(text = "Заново")
        }

        Button(onClick = {viewModel.processCommand(PasswordCommand.ChangeLength(state.length + 1))}) {
            Text(text = "Длина +1")
        }

        Button(onClick = {viewModel.processCommand(PasswordCommand.ChangeLength(state.length - 1))}) {
            Text(text = "Длина -1")
        }

        Button(onClick = {viewModel.processCommand(PasswordCommand.ToggleOption(CharOption.DIGITS))}) {
            Text(text = "Цифры вкл/выкл")
        }

        Button(onClick = {viewModel.processCommand(PasswordCommand.Copied)}) {
            Text(text = "Скопировано")
        }

        Text("23456789", color = LocalExtraColors.current.pwDigit)
        Text(state.password, style = PasswordTextStyle)
        Text("${state.length}", style = MaterialTheme.typography.displaySmall)
        Text("${state.bits} бит энтропии", style = MaterialTheme.typography.labelMedium)


        // 1. Вывести пароль, длину, биты, надёжность, опции, isCopied
        // 2. Пять кнопок
    }
}