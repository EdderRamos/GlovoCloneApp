package com.edlabcode.glovocloneapp.ui.auth.login

import com.edlabcode.glovocloneapp.ui.core.Country

sealed class LoginState {
    data class Data(
        val selectedCountry: Country = Country.Peru,
        val phoneNumber: String = "",
        val showCountrySelector: Boolean = false
    ) : LoginState()

    data object Authenticated : LoginState()
}