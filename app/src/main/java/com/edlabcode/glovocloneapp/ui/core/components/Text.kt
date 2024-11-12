package com.edlabcode.glovocloneapp.ui.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edlabcode.glovocloneapp.ui.theme.GlovoCloneAppTheme
import com.edlabcode.glovocloneapp.ui.theme.GrayColor
import com.edlabcode.glovocloneapp.ui.theme.LightGrayColor
import com.edlabcode.glovocloneapp.ui.theme.spacing


val outlinedTextFieldColors: TextFieldColors
    @Composable
    get() = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = GrayColor,
        focusedBorderColor = Color.Black,
        unfocusedContainerColor = MaterialTheme.colorScheme.background,
        focusedContainerColor = MaterialTheme.colorScheme.background
    )

@Composable
fun OutlinedTextFieldInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = { LabelText("Title") },
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = { SupportingText("Suggestion") },
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = outlinedTextFieldColors
) {
    OutlinedTextField(
        value,
        onValueChange,
        modifier,
        enabled,
        readOnly,
        textStyle,
        label,
        placeholder,
        leadingIcon,
        trailingIcon,
        prefix,
        suffix,
        supportingText,
        isError,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        singleLine, maxLines, minLines, interactionSource,
        shape, colors
    )
}

object TextFieldAppDefaults {
    @Composable
    fun label(text: String): @Composable () -> Unit = {
        LabelText(text)
    }
}

@Composable
fun TextFieldApp(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
    label: @Composable (() -> Unit)? = { LabelText() },
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
) {
    var isFocused by remember { mutableStateOf(false) }
    val color = if (isFocused) Color.Black else LightGrayColor
    TextField(
        value,
        onValueChange,
        modifier.then(
            Modifier
                .border(width = 2.dp, color = color, shape)
                .onFocusChanged { isFocused = it.isFocused }),
        enabled,
        readOnly,
        textStyle,
        label,
        placeholder,
        leadingIcon,
        trailingIcon,
        prefix,
        suffix,
        supportingText,
        isError,
        visualTransformation,
        keyboardOptions,
        keyboardActions,
        singleLine,
        maxLines,
        minLines,
        interactionSource,
        shape,
        colors
    )
}

@Composable
fun SupportingText(text: String) {
    Text(text)
}

@Composable
fun LabelText(text: String = "label") {
    Text(
        text,
        modifier = Modifier.padding(top = MaterialTheme.spacing.small)
    )
}

@Composable
fun ButtonText(
    modifier: Modifier = Modifier, text: String = "text button",
) {
    Text(
        text, modifier = modifier,
        style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp)
    )
}

@Composable
@Preview
private fun TextPreview() {
    GlovoCloneAppTheme {
        TextFieldApp(value = "Prueba", onValueChange = {})
    }
}