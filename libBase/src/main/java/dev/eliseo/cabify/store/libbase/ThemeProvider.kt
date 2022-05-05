package dev.eliseo.cabify.store.libbase

import androidx.compose.runtime.Composable

interface ThemeProvider {

    @Composable
    fun ApplyTheme(content: @Composable () -> Unit)
}