package com.edlabcode.glovocloneapp.ui.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.edlabcode.glovocloneapp.ui.theme.LightGrayColor
import com.edlabcode.glovocloneapp.ui.theme.spacing

@Composable
fun ButtonApp(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(contentColor = Color.White),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = MaterialTheme.spacing.small,
        vertical = MaterialTheme.spacing.medium
    ),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit = { ButtonText() }
) {
    Button(
        onClick,
        modifier,
        enabled,
        shape,
        colors,
        elevation,
        border,
        contentPadding,
        interactionSource,
        content
    )
}

@Composable
fun ButtonWithBorder(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    borderColor: Color = LightGrayColor,
    borderWith: Dp = 1.dp,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    contentPadding: PaddingValues = PaddingValues(
        horizontal = MaterialTheme.spacing.small,
        vertical = MaterialTheme.spacing.medium
    ),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit = { ButtonText() }
) {
    Button(
        onClick,
        modifier,
        enabled,
        shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,

            ),
        elevation,
        BorderStroke(borderWith, borderColor),
        contentPadding,
        interactionSource,
        content
    )
}