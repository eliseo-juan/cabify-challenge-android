package dev.eliseo.cabify.store

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.eliseo.cabify.core.navigation.NavigationManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()
}