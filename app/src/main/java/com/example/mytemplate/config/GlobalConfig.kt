package com.example.mytemplate.config

object GlobalConfig {
    const val isDebug = true

    // base url of application
    private const val baseUrlExample = "https://api.github.com/"
    private const val baseUrlProduction = baseUrlExample
    private const val baseUrlDev = baseUrlExample
    val baseUrl: String get() = if (isDebug) baseUrlDev else baseUrlProduction

    // shared preference
    const val sharePreferenceSession = "template_sp_session"
}