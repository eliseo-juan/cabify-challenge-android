package dev.eliseo.cabify.store

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.eliseo.cabify.core.navigation.NavigationManager
import dev.eliseo.cabify.core.navigation.directions.CheckoutNavigation
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation
import dev.eliseo.cabify.core.navigation.directions.StoreNavigation
import dev.eliseo.cabify.store.ui.checkout.CheckoutView
import dev.eliseo.cabify.store.ui.product_dialog.ProductDialogView
import dev.eliseo.cabify.feature.store.StoreView
import dev.eliseo.cabify.store.ui.theme.CabifyStoreTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            navigationManager.commands.collectAsState().value.also { command ->
                if (command.destination.isNotEmpty()) {
                    Log.d("NAVIGATION", "Navigating to ${command.destination}")
                    Log.d("NAVIGATION", navController.toString())
                    navController.navigate(command.destination)
                    //navController.navigate(route = command.destination)
                }
            }
            CabifyStoreTheme {
                NavHost(
                    navController = navController,
                    startDestination = StoreNavigation.store.destination
                ) {
                    composable(route = StoreNavigation.store.destination) {
                        StoreView(hiltViewModel(it))
                    }
                    dialog(
                        route = ProductDetailNavigation.route,
                        arguments = ProductDetailNavigation.arguments,
                    ) {
                        ProductDialogView(
                            hiltViewModel(it),
                            productId = it.arguments?.getString(ProductDetailNavigation.KEY_PRODUCT_ID)
                                ?: throw IllegalStateException("ProductId is not a string")
                        )
                    }
                    composable(CheckoutNavigation.checkout.destination) {
                        CheckoutView(navController = navController)
                    }
                }
            }
        }
    }
}