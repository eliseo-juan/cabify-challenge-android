package dev.eliseo.cabify.store.ui.di

import androidx.compose.runtime.Composable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.eliseo.cabify.store.libbase.ThemeProvider
import dev.eliseo.cabify.store.ui.theme.CabifyStoreTheme

@Module
@InstallIn(SingletonComponent::class)
class ThemeProviderModule {

    @Provides
    fun provideThemeProvider(): ThemeProvider = object : ThemeProvider {
        @Composable
        override fun ApplyTheme(content: @Composable () -> Unit) {
            CabifyStoreTheme(content = content)
        }
    }
}