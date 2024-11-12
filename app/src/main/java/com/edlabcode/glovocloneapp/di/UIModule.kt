package com.edlabcode.glovocloneapp.di

import com.edlabcode.glovocloneapp.ui.auth.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf

import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::LoginViewModel)
}