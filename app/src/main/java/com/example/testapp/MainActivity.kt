package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.testapp.screen.*
import com.example.testapp.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                //NavController to manage navigation
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginPage(navController) }
                    composable("home") { HomePage(navController) }
                    composable("favourites"){ FavouritePage(navController) }
                    composable("cart") { CartPage(navController) }
                    composable("profile") { ProfilePage(navController) }
                    composable("register") { RegisterPage(navController) }

                    composable(
                        "productDetail/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId")
                        // Find the product from data source
                        val product = findProductById(productId)
                        if (product != null) {
                            ProductDetailPage(navController, product)
                        }
                    }
                }
            }
        }
    }

    private fun findProductById(productId: Int?): Product? {
        val allProducts = listOf(
            Product(R.drawable.popular11, "Luxera White", "$599", 0),
            Product(R.drawable.popular22, "Luxera Wooden", "$799", 1),
            Product(R.drawable.popular333, "Art Chair", "$299", 2),
            Product(R.drawable.living11, "Modern Sofa", "$899", 3),
            Product(R.drawable.living22, "Turqoise Living", "$649", 4),
            Product(R.drawable.living33, "Wooden Lamp", "$199", 5),
            Product(R.drawable.bedroom11, "King's Bed", "$999", 6),
            Product(R.drawable.bedroom22, "Minimal Table", "$349", 7),
            Product(R.drawable.bedroom33, "Grand Lounge", "$749", 8),
            Product(R.drawable.bathroom11, "Antique Mirror", "$459", 9),
            Product(R.drawable.bathroom22, "Bathside Table", "$279", 10),
            Product(R.drawable.bathroom33, "Mirror Lamp", "$399", 11)
        )
        return if (productId != null && productId < allProducts.size) {
            allProducts[productId]
        } else {
            null
        }
    }
}
