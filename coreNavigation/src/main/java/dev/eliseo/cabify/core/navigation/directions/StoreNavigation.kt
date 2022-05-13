package dev.eliseo.cabify.core.navigation.directions

import androidx.navigation.NamedNavArgument
import dev.eliseo.cabify.core.navigation.NavigationCommand

object StoreNavigation {

    val default = object : NavigationCommand.Destination {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination = ""
    }

    val store = object : NavigationCommand.Destination {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "store"
    }
}