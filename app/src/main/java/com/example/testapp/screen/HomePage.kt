package com.example.testapp.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapp.R
import com.example.testapp.components.BottomNavigationBar

data class Product(
    val imageRes: Int, 
    val name: String,
    val price: String,
    val id: Int = 0
)
data class Category(val title: String, val products: List<Product>)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation
    val columns = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        val categories = listOf(
            Category(stringResource(R.string.category_popular), listOf(
                Product(R.drawable.popular11, stringResource(R.string.product_luxera_white), "$599", 0),
                Product(R.drawable.popular22, stringResource(R.string.product_luxera_wooden), "$799", 1),
                Product(R.drawable.popular333, stringResource(R.string.product_art_chair), "$299", 2)
            )),
            Category(stringResource(R.string.category_living), listOf(
                Product(R.drawable.living11, stringResource(R.string.product_modern_sofa), "$899", 3),
                Product(R.drawable.living22, stringResource(R.string.product_turqoise_living), "$649", 4),
                Product(R.drawable.living33, stringResource(R.string.product_wooden_lamp), "$199", 5)
            )),
            Category(stringResource(R.string.category_bedroom), listOf(
                Product(R.drawable.bedroom11, stringResource(R.string.product_kings_bed), "$999", 6),
                Product(R.drawable.bedroom22, stringResource(R.string.product_minimal_table), "$349", 7),
                Product(R.drawable.bedroom33, stringResource(R.string.product_grand_lounge), "$749", 8)
            )),
            Category(stringResource(R.string.category_bathroom), listOf(
                Product(R.drawable.bathroom11, stringResource(R.string.product_antique_mirror), "$459", 9),
                Product(R.drawable.bathroom22, stringResource(R.string.product_bathside_table), "$279", 10),
                Product(R.drawable.bathroom33, stringResource(R.string.product_mirror_lamp), "$399", 11)
            ))
        )

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Header item
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 7.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.luxera),
                        contentDescription = stringResource(R.string.logo_content_description),
                        modifier = Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    TextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(stringResource(R.string.search_placeholder)) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(49.dp)
                            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(25.dp))
                            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(25.dp))
                    )
                }
            }

            // Categories
            categories.forEach { category ->
                item {
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(category.products.size) { index ->
                            val product = category.products[index]
                            ProductCard(
                                product = product,
                                onClick = { navController.navigate("productDetail/${product.id}") }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = stringResource(R.string.product_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
