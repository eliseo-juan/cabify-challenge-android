package dev.eliseo.cabify.store.ui

sealed class AppDestinations(val route: String) {

    object Store : AppDestinations("store")

    object ProductDialog : AppDestinations("productDialog/{productId}") {

        fun getNavigationLink(productId: String) =
            route.replace("{productId}", productId)
    }

    object Checkout : AppDestinations("checkout")
}