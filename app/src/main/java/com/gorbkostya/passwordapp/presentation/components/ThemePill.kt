package com.gorbkostya.passwordapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gorbkostya.passwordapp.R

@Composable
fun ThemePill(
    isDark: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        onClick = onToggle,
        modifier = modifier.height(44.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            if (isDark) {
                Icon(
                    painter = painterResource(R.drawable.ic_light_mode),
                    contentDescription = null
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_dark_mode),
                    contentDescription = null
                )
            }

            Spacer(Modifier.width(8.dp))

            if (isDark) {
                Text(
                    text = "Светлая",
                    style = MaterialTheme.typography.labelLarge,
                )
            } else {
                Text(
                    text = "Темная",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}
