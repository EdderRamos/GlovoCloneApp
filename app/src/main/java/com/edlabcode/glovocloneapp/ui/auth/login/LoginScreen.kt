package com.edlabcode.glovocloneapp.ui.auth.login

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.edlabcode.glovocloneapp.R
import com.edlabcode.glovocloneapp.ui.auth.login.LoginViewModel.UIEvent
import com.edlabcode.glovocloneapp.ui.core.Country
import com.edlabcode.glovocloneapp.ui.core.components.ButtonApp
import com.edlabcode.glovocloneapp.ui.core.components.ButtonText
import com.edlabcode.glovocloneapp.ui.core.components.ButtonWithBorder
import com.edlabcode.glovocloneapp.ui.core.components.LabelText
import com.edlabcode.glovocloneapp.ui.core.components.TextFieldApp
import com.edlabcode.glovocloneapp.ui.core.dialogs.CountryBottomSheet
import com.edlabcode.glovocloneapp.ui.theme.GlovoCloneAppTheme
import com.edlabcode.glovocloneapp.ui.theme.GrayColor
import com.edlabcode.glovocloneapp.ui.theme.LightGrayColor
import com.edlabcode.glovocloneapp.ui.theme.spacing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    state: LoginState.Data,
    onEvent: (UIEvent) -> Unit
) {
    val isBodyShowing = remember { mutableStateOf(false) }
    val weight = rememberFloatAnimation(0.01f, 0.7f) { isBodyShowing.value = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        if (isBodyShowing.value) {
            FoodAnimation(
                Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(1f)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f)
        ) {
            Header(
                Modifier
                    .weight(.1f)
                    .zIndex(2f)
            )
            Body(
                Modifier.fillMaxHeight(weight.value),
                state,
                isBodyShowing.value,
                onEvent
            )
        }
    }

}

@Composable
private fun Header(modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.ic_full_logo), contentDescription = "logo",
            modifier = Modifier
                .width(MaterialTheme.spacing.logoMaxSpace)
        )

    }
}

@Composable
private fun FoodAnimation(modifier: Modifier) {
    val imageWeight by rememberFloatAnimation(0.01f, 1f, durationMillis = 600)
    val animation = rememberInfiniteTransition("infinity")
    val rotation by animation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 50000,
                easing = LinearEasing
            )
        ),
        label = "rotation_animation"
    )
    Box(modifier.fillMaxHeight(.5f), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .offset(y = (-60f).dp)
                .rotate(rotation)
                .scale(1.3f)
                .fillMaxWidth(imageWeight),
            painter = painterResource(R.drawable.ic_food), contentDescription = "around_food",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun Body(
    modifier: Modifier,
    state: LoginState.Data,
    showBody: Boolean,
    onEvent: (UIEvent) -> Unit
) {
    val spacing = MaterialTheme.spacing
    var isOtherMethodsVisible by remember { mutableStateOf(false) }

    Card(
        modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topStart = spacing.medium, topEnd = spacing.medium),
        elevation = CardDefaults.elevatedCardElevation(spacing.small)
    ) {
        AnimatedVisibility(
            showBody,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = 600)
            ) + fadeIn(),
        ) {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding()
                    .padding(horizontal = spacing.small, vertical = spacing.small)
                    .padding(vertical = spacing.small, horizontal = spacing.small)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(spacing.extraSmall),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Welcome()
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = spacing.medium)
                        .height(spacing.extraLarge),
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                ) {
                    Prefix(
                        Modifier
                            .weight(.45f)
                            .fillMaxHeight(), state.selectedCountry,
                        onClickToChange = {
                            onEvent(UIEvent.ShowCountrySelector(true))
                        }
                    )
                    Number(
                        Modifier
                            .weight(.55f)
                            .fillMaxHeight(),
                        state.phoneNumber,
                        onValueChange = { phoneNumber ->
                            onEvent(UIEvent.OnInputsChanged(state.selectedCountry, phoneNumber))
                        }
                    )
                }

                Row(Modifier.fillMaxWidth()) {
                    SMS(Modifier.weight(1f))
                    Spacer(Modifier.width(spacing.small))
                    WhatsApp(Modifier.weight(1f))
                }
                Spacer(Modifier.height(spacing.small))
                Divider()
                Spacer(Modifier.height(spacing.small))
                ButtonItem(
                    text = stringResource(R.string.text_google),
                    icon = R.drawable.ic_google_logo
                ) {}
                Spacer(Modifier.height(spacing.small))
                AnimatedVisibility(isOtherMethodsVisible) {
                    Column(verticalArrangement = Arrangement.spacedBy(spacing.medium)) {
                        ButtonItem(
                            text = stringResource(R.string.text_facebook),
                            icon = R.drawable.ic_facebok_logo
                        ) {}
                        ButtonItem(
                            text = stringResource(R.string.text_email),
                            icon = R.drawable.ic_mail
                        ) {}
                    }
                }
                if (!isOtherMethodsVisible) OtherMethodsButton { isOtherMethodsVisible = true }
                TermsAndConditions()
            }
        }
    }
    //Dialogs
    if (state.showCountrySelector) {
        CountryBottomSheet(
            selectedCountry = state.selectedCountry,
            onCountryChange = { newCountry ->
                onEvent(
                    UIEvent.OnInputsChanged(prefix = newCountry, phoneNumber = state.phoneNumber)
                )
            },
            onDismiss = { onEvent(UIEvent.ShowCountrySelector(false)) }
        )
    }

}

@Composable
private fun TermsAndConditions() {
    val annotatedText = buildAnnotatedString {
        withLink(LinkAnnotation.Url(url = "https://glovoapp.com/docs/en/legal/terms/")) {
            append("By continuing, you automatically accept our ")
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Terms and Conditions")
            }
        }
        append(", ")

        withLink(LinkAnnotation.Url(url = "https://glovoapp.com/docs/en/legal/privacy/")) {
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Privacy Policy")
            }
        }
        append(" and ")
        withLink(LinkAnnotation.Url(url = "https://glovoapp.com/en/legal/cookies/")) {
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Cookies Policy")
            }
        }
    }
    Text(
        text = annotatedText, style = MaterialTheme.typography.bodySmall,
        color = GrayColor, modifier = Modifier.padding(MaterialTheme.spacing.medium),
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun OtherMethodsButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
    ) {
        ButtonText(text = "Other methods")
    }
}

@Composable
private fun ButtonItem(
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    ButtonWithBorder(
        onClick,
        contentPadding = PaddingValues(MaterialTheme.spacing.small)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.spacing.small)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = "icon",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(MaterialTheme.spacing.large)
            )
            ButtonText(
                text = text, modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
private fun Divider() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(color = LightGrayColor, modifier = Modifier.weight(1.5f))
        Text(
            "or with", modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        HorizontalDivider(color = LightGrayColor, modifier = Modifier.weight(1.5f))
    }
}

@Composable
private fun WhatsApp(modifier: Modifier) {
    ButtonApp(
        onClick = {},
        modifier = modifier,
        content = { ButtonText(text = "WhatsApp") },
    )
}

@Composable
private fun SMS(modifier: Modifier) {
    ButtonWithBorder(onClick = {},
        modifier = modifier,
        content = { ButtonText(text = "SMS") })
}

@Composable
private fun Prefix(
    modifier: Modifier = Modifier,
    prefix: Country,
    onClickToChange: () -> Unit = {}
) {

    TextFieldApp(
        modifier = modifier,
        value = prefix.getCountrySet(), onValueChange = {},
        label = { LabelText("Prefix") },
        trailingIcon = {
            IconButton(onClick = onClickToChange) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "up_arrow"
                )
            }
        },
        singleLine = true,
        readOnly = true,
    )
}

@Composable
private fun Number(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onValueChange: (String) -> Unit
) {
    TextFieldApp(
        phoneNumber, onValueChange = onValueChange,
        label = { LabelText("Phone number") },
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
    )
}

@Composable
private fun Welcome() {
    Text(
        text = stringResource(R.string.text_welcome),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Text(
        text = stringResource(R.string.text_lets_start_phone_number),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun rememberFloatAnimation(
    from: Float, to: Float,
    durationMillis: Int = 1800,
    onFinished: (Float) -> Unit = {}
): State<Float> {
    val changer = remember(from) { mutableFloatStateOf(from) }
    LaunchedEffect(to) {
        launch(Dispatchers.IO) {
            delay(200)
            changer.floatValue = to
        }
    }
    return animateFloatAsState(
        targetValue = changer.floatValue,
        visibilityThreshold = to,
        label = "weight_animation",
        animationSpec = tween(durationMillis),
        finishedListener = onFinished
    )
}

@Composable
@Preview
private fun LoginPreview() {
    GlovoCloneAppTheme {
        LoginScreen(LoginState.Data(), {})
    }
}