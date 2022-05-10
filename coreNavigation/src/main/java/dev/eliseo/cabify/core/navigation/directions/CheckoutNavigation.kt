package dev.eliseo.cabify.core.navigation.directions

import androidx.navigation.NamedNavArgument
import dev.eliseo.cabify.core.navigation.NavigationCommand

object CheckoutNavigation {

    val checkout = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "checkout"
    }
}