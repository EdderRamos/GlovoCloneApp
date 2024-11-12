package com.edlabcode.glovocloneapp.ui.core.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.edlabcode.glovocloneapp.R
import com.edlabcode.glovocloneapp.ui.core.Country
import com.edlabcode.glovocloneapp.ui.core.components.TextFieldApp
import com.edlabcode.glovocloneapp.ui.core.components.TextFieldAppDefaults
import com.edlabcode.glovocloneapp.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryBottomSheet(
    countries: List<Country> = Country.list,
    selectedCountry: Country,
    onDismiss: () -> Unit,
    onCountryChange: (Country) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismiss,
        dragHandle = {
            Header(Modifier.height(MaterialTheme.spacing.extraLarge)) {
                scope.launch { sheetState.hide() }.invokeOnCompletion { onDismiss() }
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        sheetState = sheetState,
        modifier = Modifier.statusBarsPadding()
    ) {
        Body(
            modifier = Modifier
                .fillMaxSize(),
            countries = countries, selectedCountry = selectedCountry
        ) { newCountry ->
            scope.launch {
                onCountryChange(newCountry)
                sheetState.hide()
            }.invokeOnCompletion { onDismiss() }
        }
    }
}

@Composable
private fun Body(
    modifier: Modifier = Modifier, countries: List<Country>,
    selectedCountry: Country,
    onCountryChange: (Country) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }

    val sortedList = remember(query) {
        countries.filter { country ->
            country.name.contains(query, ignoreCase = true)
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        item { SearchCountry(query, onValueChange = { new -> query = new }) }
        item { Spacer(Modifier.height(MaterialTheme.spacing.large)) }
        items(sortedList.size) { index ->
            val country = sortedList[index]
            CountryItem(country, country == selectedCountry) {
                onCountryChange(country)
            }
        }
    }
}

@Composable
private fun SearchCountry(
    query: String,
    onValueChange: (String) -> Unit
) {
    TextFieldApp(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
            .fillMaxWidth(),
        value = query, onValueChange = onValueChange,
        label = TextFieldAppDefaults.label("Search for a country")
    )
}

@Composable
private fun CountryItem(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.extraLarge)
            .clickable { onClick() }
            .padding(horizontal = MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(country.getCountryOption(), modifier = Modifier.weight(1f))
        RadioButton(
            isSelected, onClick = onClick,
            modifier = Modifier.size(MaterialTheme.spacing.large)
        )
    }
}

@Composable
private fun Header(modifier: Modifier, onBack: () -> Unit) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onBack
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "left_arrow"
            )
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.text_title_select_a_country),
            style = MaterialTheme.typography.titleMedium
        )
    }
}