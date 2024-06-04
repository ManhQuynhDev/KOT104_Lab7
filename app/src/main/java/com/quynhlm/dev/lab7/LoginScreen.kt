package com.quynhlm.dev.lab7

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.quynhlm.dev.lab7.R
import com.quynhlm.dev.lab7.ViewModel.LoginViewModel


@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = viewModel()
    LoginCard(navController, loginViewModel)
}

@Composable
fun LoginCard(navController: NavController, loginViewModel: LoginViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    HandleLoginState(snackbarHostState, loginViewModel, navController)
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LoginScreen(loginViewModel, paddingValues , navController)
    }
}
@Composable
fun HandleLoginState(
    snackbarHostState: SnackbarHostState,
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val isAuthenticated by loginViewModel.isAuthenticated.observeAsState()
    LaunchedEffect(key1 = isAuthenticated) {
        when (isAuthenticated) {
            true -> {
                navController.navigate(Screen.MOVIE_SCREEN.route) {
                    popUpTo(Screen.LOGIN.route) { inclusive = true }
                }
            }

            false -> {
                snackbarHostState.showSnackbar(
                    message = "Invalid username or password.",
                    duration = SnackbarDuration.Short
                )
                loginViewModel.resetAuthenticationState()
            }

            null -> {}
        }
    }
}

@Composable
fun LoginScreen(loginViewModel: LoginViewModel,
                paddingValues: PaddingValues,
                navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") } // State to store username
    var password by remember { mutableStateOf("") } // State to store password
    var isRemember by remember { mutableStateOf(false) } // State to store switch state
    var showDialog by remember { mutableStateOf(false) } // State to store dialog visibility
    var dialogMessage by remember { mutableStateOf("") } // State to store dialog message

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        OutlinedTextField(
            label = { Text(text = "Username") },
            placeholder = { Text(text = "Username") },
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Password") },
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = isRemember,
                onCheckedChange = { isRemember = it }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Remember Me?")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (username.isEmpty() || password.isEmpty()) {
                        dialogMessage = "Please don't leave fields empty"
                    } else {
                        if (username == "admin" && password == "123") {
                            dialogMessage = "Login Successfully"
                            navController.navigate(Screen.MOVIE_SCREEN.route)
                        } else {
                            dialogMessage = "Login not Successfully"
                        }
                    }
                    showDialog = true // Show dialog
                }
                .padding(start = 20.dp, end = 20.dp)
                .background(Color.Black, shape = RoundedCornerShape(20.dp))
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Login", color = Color.White)
        }
    }

    // Show dialog if needed
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Login Status") },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "OK")
                }
            }
        )
    }
}
