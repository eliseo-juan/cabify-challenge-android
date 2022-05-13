package dev.eliseo.cabify.core.navigation

import dev.eliseo.cabify.core.navigation.directions.StoreNavigation
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    var commands = MutableStateFlow<NavigationCommand>(StoreNavigation.default)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}