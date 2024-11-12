package com.edlabcode.glovocloneapp.ui.auth.login

import androidx.lifecycle.ViewModel
import com.edlabcode.glovocloneapp.ui.core.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState.Data())
    val state = _state.asStateFlow()

    sealed class UIEvent {
        data class OnInputsChanged(val prefix: Country, val phoneNumber: String) : UIEvent()
        data class ShowCountrySelector(val show: Boolean) : UIEvent()
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.OnInputsChanged ->
                _state.update {
                    it.copy(selectedCountry = event.prefix, phoneNumber = event.phoneNumber)
                }

            is UIEvent.ShowCountrySelector ->
                _state.update { it.copy(showCountrySelector = event.show) }
        }
    }
}

