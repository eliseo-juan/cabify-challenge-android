package dev.eliseo.cabify.store

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.eliseo.cabify.core.navigation.NavigationCommand
import dev.eliseo.cabify.core.navigation.NavigationManager
import dev.eliseo.cabify.core.navigation.directions.CheckoutNavigation
import dev.eliseo.cabify.core.navigation.directions.ProductDetailNavigation
import dev.eliseo.cabify.core.navigation.directions.StoreNavigation
import dev.eliseo.cabify.feature.checkout.CheckoutView
import dev.eliseo.cabify.feature.productdetail.ProductDialogView
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
            // Remember a SystemUiController
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            SideEffect {
                // Update all of the system bar colors to be transparent, and use
                // dark icons if we're in light theme
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )

                // setStatusBarsColor() and setNavigationBarColor() also exist
            }

            val navController = rememberNavController()
            navigationManager.commands.collectAsState().value.also { command ->
                when {
                    command is NavigationCommand.GoBack -> {
                        navController.popBackStack()
                    }
                    command is NavigationCommand.Destination && command.destination.isNotEmpty() ->
                        navController.navigate(command.destination)
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
                            hiltViewModel(it)
                        )
                    }

                    composable(CheckoutNavigation.checkout.destination) {
                        CheckoutView(hiltViewModel(it))

                    }
                }
            }
        }
    }
}