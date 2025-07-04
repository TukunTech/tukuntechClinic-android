package pe.edu.upc.tukuntech.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.tukuntech.R
import pe.edu.upc.tukuntech.presentation.di.PresentationModule

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }

    val viewModel = remember { PresentationModule.getAuthViewModel() }
    val loginResult = viewModel.loginResult.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.login_screen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.tukuntech_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(10.dp)
                .size(120.dp)
        )

        Box(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(120.dp)
        ) {
            Text("Log in", color = Color.White, fontSize = 25.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White) // Fondo sólido para mayor contraste
                .padding(40.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(R.drawable.google, R.drawable.ios, R.drawable.facebook).forEach { iconRes ->
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFE0F7F9))
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "CLINIC",
                    fontSize = 18.sp,
                    color = Color(0xFF0c95a2),
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedTextField(
                label = { Text("Email", color = Color.DarkGray) },
                value = email.value,
                onValueChange = { email.value = it },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                isError = loginResult.value?.isFailure == true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                label = { Text("Password", color = Color.DarkGray) },
                value = password.value,
                onValueChange = { password.value = it },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (showPassword.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = {
                        showPassword.value = !showPassword.value
                    }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Toggle password visibility",
                            tint = Color.DarkGray
                        )
                    }
                },
                isError = loginResult.value?.isFailure == true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.size(150.dp, 40.dp),
                onClick = {
                    viewModel.login(email.value, password.value)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0c95a2)
                )
            ) {
                Text("Log In", color = Color.White)
            }

            if (loginResult.value?.isFailure == true) {
                Text(
                    text = "Incorrect username or password",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            LaunchedEffect(loginResult.value) {
                loginResult.value?.onSuccess { token ->
                    println("✅ Login OK. Token: $token")
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }?.onFailure { error ->
                    println("Login fallido: ${error.message}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("I forgot my password", color = Color.Black)

            HorizontalDivider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text("Don't have an account?", color = Color.Black)

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate("create_account")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0FA596)
                )
            ) {
                Text("Create an account", color = Color.White)
            }
        }
    }
}

