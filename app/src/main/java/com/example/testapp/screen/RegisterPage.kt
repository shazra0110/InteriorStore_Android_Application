package com.example.testapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.testapp.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.random2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Make the content scrollable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Luxera Logo
            Image(
                painter = painterResource(id = R.drawable.luxera),
                contentDescription = stringResource(R.string.logo_content_description),
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 32.dp)
                    .height(60.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Register content
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Main title
                Text(
                    text = stringResource(R.string.register),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Name TextField
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    ),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_check), contentDescription = null) }
                )

                // Email TextField
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    ),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_check), contentDescription = null) }
                )

                // Password TextField
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_visibility_off), contentDescription = null) }
                )

                // Confirm Password TextField
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Confirm Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_visibility_off), contentDescription = null) }
                )

                // Register Button
                Button(
                    onClick = { 
                        navController.navigate("home")  // Changed to navigate to home
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(text = "Register", color = Color.White)
                }

                // Login Link
                Text(
                    text = "Already have an account? Login",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .clickable {
                            navController.navigate("login")
                        }
                )

                // Social Media Icons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_facebook), contentDescription = null, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(painter = painterResource(id = R.drawable.ic_twitter), contentDescription = null, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(painter = painterResource(id = R.drawable.ic_google), contentDescription = null, modifier = Modifier.size(48.dp))
                }
            }
        }
    }
}
