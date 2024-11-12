package com.edlabcode.glovocloneapp.ui.core

interface UICountry {
    fun getCountrySet(): String
    fun getCountryOption(): String
}

open class UICountryBase(
    private val code: Int,
    private val name: String,
    private val flag: String
) : UICountry {
    override fun getCountrySet() = "$flag+$code"

    override fun getCountryOption() = "$flag $name (+$code)"
}

sealed class Country(
    val code: Int,
    val name: String,
    val flag: String
) : UICountry by UICountryBase(code, name, flag) {

    data object Peru : Country(51, "Perú", flag = "\uD83C\uDDF5\uD83C\uDDEA")
    data object Argentina : Country(54, "Argentina", flag = "\uD83C\uDDE6\uD83C\uDDF7")
    data object Spain : Country(34, "Spain", flag = "\uD83C\uDDEA\uD83C\uDDF8")

    companion object {
        val list by lazy { listOf(Peru, Argentina, Spain) }
        fun init() {

        }
    }
}
