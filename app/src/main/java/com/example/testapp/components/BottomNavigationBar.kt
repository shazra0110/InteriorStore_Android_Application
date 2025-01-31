package com.example.testapp.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapp.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = { 
                Icon(
                    painter = painterResource(id = R.drawable.icon_home),
                    contentDescription = stringResource(R.string.nav_home),
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "home") 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            label = { Text(stringResource(R.string.nav_home)) },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )

        NavigationBarItem(
            icon = { 
                Icon(
                    painter = painterResource(id = R.drawable.icon_heart),
                    contentDescription = stringResource(R.string.nav_favorites),
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "favourites") 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            label = { Text(stringResource(R.string.nav_favorites)) },
            selected = currentRoute == "favourites",
            onClick = { navController.navigate("favourites") }
        )

        NavigationBarItem(
            icon = { 
                Icon(
                    painter = painterResource(id = R.drawable.icon_cart),
                    contentDescription = stringResource(R.string.nav_cart),
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "cart") 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            label = { Text(stringResource(R.string.nav_cart)) },
            selected = currentRoute == "cart",
            onClick = { navController.navigate("cart") }
        )

        NavigationBarItem(
            icon = { 
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = stringResource(R.string.nav_profile),
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "profile") 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            label = { Text(stringResource(R.string.nav_profile)) },
            selected = currentRoute == "profile",
            onClick = { navController.navigate("profile") }
        )
    }
} 