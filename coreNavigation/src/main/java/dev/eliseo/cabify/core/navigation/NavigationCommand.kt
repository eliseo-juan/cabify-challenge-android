package dev.eliseo.cabify.core.navigation

import androidx.navigation.NamedNavArgument

sealed interface NavigationCommand {
    interface Destination : NavigationCommand {

        val arguments: List<NamedNavArgument>

        val destination: String
    }
    interface GoBack : NavigationCommand
}