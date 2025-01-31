package com.example.testapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testapp.R
import com.example.testapp.components.BottomNavigationBar
import kotlinx.coroutines.launch

data class UserProfile(
    var firstName: String,
    var lastName: String,
    var email: String,
    var address: String,
    var paymentMethod: String
)

object ProfileManager {
    var userProfile = UserProfile(
        firstName = "Charles",
        lastName = "Leclerc",
        email = "charles@gmail.com",
        address = "10/A, Kandy",
        paymentMethod = "Select Payment Method"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavHostController) {
    var firstName by remember { mutableStateOf(ProfileManager.userProfile.firstName) }
    var lastName by remember { mutableStateOf(ProfileManager.userProfile.lastName) }
    var email by remember { mutableStateOf(ProfileManager.userProfile.email) }
    var address by remember { mutableStateOf(ProfileManager.userProfile.address) }
    var expanded by remember { mutableStateOf(false) }
    var selectedPaymentMethod by remember { mutableStateOf(ProfileManager.userProfile.paymentMethod) }
    val paymentMethods = listOf("Cash on Delivery", "Card Payment")
    
    val scope = rememberCoroutineScope()
    var showSaveConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { 
                    Text(
                        stringResource(R.string.profile_title),
                        color = MaterialTheme.colorScheme.onSurface
                    ) 
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.personal_information),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // First Name
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(stringResource(R.string.first_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                    )
                )

                // Last Name
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text(stringResource(R.string.last_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                    )
                )

                // Email
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(R.string.email)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                    )
                )

                // Address
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text(stringResource(R.string.address)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                    )
                )

                // Payment Method Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = selectedPaymentMethod,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.payment_method)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        paymentMethods.forEach { method ->
                            DropdownMenuItem(
                                text = { Text(method) },
                                onClick = {
                                    selectedPaymentMethod = method
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Save Changes Button
                Button(
                    onClick = {
                        ProfileManager.userProfile = UserProfile(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            address = address,
                            paymentMethod = selectedPaymentMethod
                        )
                        showSaveConfirmation = true
                        scope.launch {
                            kotlinx.coroutines.delay(2000)
                            showSaveConfirmation = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.save_changes),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                if (showSaveConfirmation) {
                    Text(
                        text = stringResource(R.string.changes_saved),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Sign Out Button
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text(
                        text = stringResource(R.string.sign_out),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileOption(icon: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 18.sp, modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.prof),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
