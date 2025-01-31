package com.example.testapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testapp.R
import com.example.testapp.components.BottomNavigationBar

object FavoriteManager {
    private val _favoriteItems = mutableStateListOf<Product>()
    val favoriteItems: List<Product> = _favoriteItems

    fun addToFavorites(product: Product) {
        if (!_favoriteItems.contains(product)) {
            _favoriteItems.add(product)
        }
    }

    fun removeFromFavorites(product: Product) {
        _favoriteItems.remove(product)
    }

    fun isFavorite(product: Product): Boolean {
        return _favoriteItems.contains(product)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritePage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favourite Items",
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn {
                items(FavoriteManager.favoriteItems) { product ->
                    FavouriteItem(
                        imageRes = product.imageRes,
                        price = product.price,
                        name = product.name,
                        onRemove = { FavoriteManager.removeFromFavorites(product) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun FavouriteItem(
    imageRes: Int,
    price: String,
    name: String,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Gray)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(114.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = onRemove) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_heart),
                    contentDescription = "Remove",
                    tint = Color.Red
                )
            }
        }
    }
}



