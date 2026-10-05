package com.gorbkostya.passwordapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gorbkostya.passwordapp.R
import com.gorbkostya.passwordapp.domain.Strength
import com.gorbkostya.passwordapp.ui.theme.LocalExtraColors
import com.gorbkostya.passwordapp.ui.theme.PasswordTextStyle


@Composable
fun PasswordCard(
    password: String,
    strength: Strength,
    bits: Int,
    onRegenerate: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier.padding(start = 24.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ваш пароль",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                IconButton(
                    onClick = onRegenerate,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_refresh),
                        contentDescription = "Сгенерировать заново"
                    )
                }
            }


            val extra = LocalExtraColors.current
            val letterColor = MaterialTheme.colorScheme.onSurface

            val coloredPassword = buildAnnotatedString {
                for (ch in password) {
                    val color = when {
                        ch.isDigit() -> extra.pwDigit
                        ch.isLetter() -> letterColor
                        else -> extra.pwSymbol
                    }

                    withStyle(SpanStyle(color = color)) {
                        append(ch)
                    }
                }
            }


            Text(
                text = coloredPassword,
                style = PasswordTextStyle,
                minLines = 2,
                maxLines = 2
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val fillColor = when(strength) {
                    Strength.WEAK -> MaterialTheme.colorScheme.primary
                    Strength.MEDIUM -> LocalExtraColors.current.strengthMedium
                    Strength.STRONG -> LocalExtraColors.current.strengthStrong
                    Strength.VERY_STRONG -> LocalExtraColors.current.strengthStrong
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    repeat(4) { index ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(
                                    if (index < strength.segments) {
                                        fillColor
                                    } else {
                                        MaterialTheme.colorScheme.surfaceVariant
                                    }
                                )
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = strength.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )

                    Text(
                        text = "$bits бит энтропии",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            }


        }
    }
}