package com.gorbkostya.passwordapp.presentation

import android.content.ClipData
import android.os.PersistableBundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gorbkostya.passwordapp.presentation.components.CopyButton
import com.gorbkostya.passwordapp.presentation.components.LengthCard
import com.gorbkostya.passwordapp.presentation.components.OptionsCard
import com.gorbkostya.passwordapp.presentation.components.ThemePill
import kotlinx.coroutines.launch


@Composable
fun PasswordContent(
    state: PasswordState,
    onCommand: (PasswordCommand) -> Unit,
    onCopyClick: () -> Unit,
    isDark: Boolean,
    onToggleTheme: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Пароли", style = MaterialTheme.typography.titleMedium)
                ThemePill(isDark = isDark, onToggle = onToggleTheme)
            }

            PasswordCard(
                password = state.password,
                strength = state.strength,
                bits = state.bits,
                onRegenerate = {onCommand(PasswordCommand.Regenerate)},
            )

            LengthCard(
                length = state.length,
                onLengthChange = {onCommand(PasswordCommand.ChangeLength(it))}
            )

            OptionsCard(
                selected = state.options,
                onToggle = {onCommand(PasswordCommand.ToggleOption(it))}
            )



        }

        Spacer(Modifier.height(12.dp))

        CopyButton(
            isCopied = state.isCopied,
            onClick = onCopyClick
        )
    }
}



@Composable
fun PasswordScreen(
    viewModel: PasswordViewModel = viewModel(),
    isDark: Boolean,
    onToggleTheme: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val clipboard = LocalClipboard.current
    val haptics = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()

    PasswordContent(
        state = state,
        onCommand = viewModel::processCommand,
        onCopyClick = {
            scope.launch {
                val clip = ClipData.newPlainText("Пароль", state.password).apply {
                    description.extras = PersistableBundle().apply {
                        putBoolean("android.content.extra.IS_SENSITIVE", true)
                    }
                }
                clipboard.setClipEntry(ClipEntry(clip))
                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                viewModel.processCommand(PasswordCommand.Copied)
            }
        },
        isDark = isDark,
        onToggleTheme = onToggleTheme
    )
}
