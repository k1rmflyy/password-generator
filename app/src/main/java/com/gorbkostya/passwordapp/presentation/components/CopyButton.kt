package com.gorbkostya.passwordapp.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gorbkostya.passwordapp.R

@Composable
fun CopyButton(
    isCopied: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val containerColor by animateColorAsState(
        targetValue = if (isCopied) {
            MaterialTheme.colorScheme.onSurface
        } else {
            MaterialTheme.colorScheme.primary
        },
        label = "copyButtonContainer"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isCopied) {
            MaterialTheme.colorScheme.background
        } else {
            MaterialTheme.colorScheme.onPrimary
        },
        label = "copyButtonContent"
    )


    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        AnimatedContent(targetState = isCopied, label = "copyButtonLabel") { copied ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (copied) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        contentDescription = null
                    )
                }

                Spacer(Modifier.width(10.dp))

                if (copied) {
                    Text(
                        text = "Скопировано",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                } else {
                    Text(
                        text = "Скопировать",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}
