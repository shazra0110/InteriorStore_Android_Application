package com.example.testapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapp.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailPage(
    navController: NavController,
    product: Product
) {
    var showAddedToCart by remember { mutableStateOf(false) }
    var showAddedToFavorites by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.product_details)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.price,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        CartManager.addToCart(product)
                        showAddedToCart = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.add_to_cart))
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        FavoriteManager.addToFavorites(product)
                        showAddedToFavorites = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (FavoriteManager.isFavorite(product))
                            stringResource(R.string.added_to_favorites)
                        else
                            stringResource(R.string.add_to_favorites)
                    )
                }

                if (showAddedToCart) {
                    Text(
                        text = stringResource(R.string.added_to_cart),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    LaunchedEffect(Unit) {
                        delay(2000)
                        showAddedToCart = false
                    }
                }

                if (showAddedToFavorites) {
                    Text(
                        text = stringResource(R.string.added_to_favorites),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    LaunchedEffect(Unit) {
                        delay(2000)
                        showAddedToFavorites = false
                    }
                }
            }
        }
    }
} 