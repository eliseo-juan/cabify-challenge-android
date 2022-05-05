package dev.eliseo.cabify.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.eliseo.cabify.store.ui.AppDestinations
import dev.eliseo.cabify.store.ui.checkout.CheckoutView
import dev.eliseo.cabify.store.ui.product_dialog.ProductDialogView
import dev.eliseo.cabify.store.ui.store.StoreView
import dev.eliseo.cabify.store.ui.theme.CabifyStoreTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CabifyStoreTheme {
                NavHost(navController = navController, startDestination = "store") {
                    composable(route = AppDestinations.Store.route) { StoreView(navController = navController) }
                    dialog(
                        route = AppDestinations.ProductDialog.route,
                        arguments = listOf(navArgument("productId") { type = NavType.StringType })
                    ) { ProductDialogView(navController = navController) }
                    composable(AppDestinations.Checkout.route) { CheckoutView(navController = navController) }
                }
            }
        }
    }
}