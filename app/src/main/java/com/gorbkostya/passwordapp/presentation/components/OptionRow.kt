package com.gorbkostya.passwordapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gorbkostya.passwordapp.domain.CharOption
import com.gorbkostya.passwordapp.ui.theme.JetBrainsMono
import com.gorbkostya.passwordapp.ui.theme.LocalExtraColors

@Composable
fun OptionRow(
    option: CharOption,
    checked: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = { onToggle() }
            )
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = option.title,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = option.sample,
            fontFamily = JetBrainsMono,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(Modifier.weight(1f))

        Switch(
            checked = checked,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                checkedThumbColor = Color.White,
                uncheckedTrackColor = LocalExtraColors.current.switchOff,
                uncheckedThumbColor = Color.White,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

