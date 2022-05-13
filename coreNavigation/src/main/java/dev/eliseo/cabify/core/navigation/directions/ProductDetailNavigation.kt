package dev.eliseo.cabify.core.navigation.directions

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dev.eliseo.cabify.core.navigation.NavigationCommand

object ProductDetailNavigation {

    const val KEY_PRODUCT_ID = "productId"
    val route = "productDetail/{$KEY_PRODUCT_ID}"
    val arguments = listOf(
        navArgument(KEY_PRODUCT_ID) { type = NavType.StringType }
    )

    fun productDetailDialog(productId: String)  = object : NavigationCommand.Destination {
        override val arguments: List<NamedNavArgument> = ProductDetailNavigation.arguments
        override val destination: String = "productDetail/$productId"
    }

}