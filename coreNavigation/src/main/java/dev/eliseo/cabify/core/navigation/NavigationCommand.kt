package dev.eliseo.cabify.core.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val arguments: List<NamedNavArgument>

    val destination: String
}