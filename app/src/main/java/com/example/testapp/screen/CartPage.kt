package com.example.testapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testapp.R
import com.example.testapp.components.BottomNavigationBar

object CartManager {
    private val _cartItems = mutableStateListOf<Product>()
    val cartItems: List<Product> = _cartItems

    fun addToCart(product: Product) {
        _cartItems.add(product)
    }

    fun removeFromCart(product: Product) {
        _cartItems.remove(product)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cart",
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
                items(CartManager.cartItems) { product ->
                    CartItem(
                        imageRes = product.imageRes,
                        price = product.price,
                        name = product.name,
                        onQuantityChange = { /* Handle quantity change */ }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                
                item {
                    val total = CartManager.cartItems.sumOf { 
                        it.price.removePrefix("$").toDouble()
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "$${String.format("%.2f", total)}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* Handle checkout */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text(text = "Checkout", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun CartItem(
    imageRes: Int,
    price: String,
    name: String,
    onQuantityChange: (Int) -> Unit
) {
    val quantity = remember { mutableStateOf(1) }

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
            QuantitySelector(
                quantity = quantity.value,
                onQuantityChange = {
                    quantity.value = it
                    onQuantityChange(it)
                }
            )
        }
    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            if (quantity > 1) onQuantityChange(quantity - 1)
        }) {
            Text(text = "-", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black))
        }
        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        IconButton(onClick = { onQuantityChange(quantity + 1) }) {
            Text(text = "+", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black))
        }
    }
}
