package com.edlabcode.glovocloneapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            uiModule,
            domainModule,
            dataModule
        )
    }
}