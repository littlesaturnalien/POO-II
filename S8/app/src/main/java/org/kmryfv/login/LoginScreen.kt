package org.kmryfv.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kmryfv.login.models.LoginViewModel
import org.kmryfv.login.ui.theme.LoginTheme

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginTheme {
            }
        }
    }
}

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, onLoginSuccess: () -> Unit) {
    val context = LocalContext.current

    var user by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    var showPassword by remember { mutableStateOf(false)}
    var credentialsError by remember { mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = user,
            onValueChange = {
                user = it
                credentialsError = false
            },
            label = { Text("Usuario")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                credentialsError = false
            },
            label = { Text("Contraseña")},
            trailingIcon = {
                val textIcon = if (showPassword) "Ocultar" else "Mostrar"
                TextButton(onClick = {showPassword = !showPassword}) {
                    Text(textIcon)
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (credentialsError) {
            Text(
                text = "Credenciales incorrectas.",
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = {
                loginViewModel.login(user, password)
                if (loginViewModel.isLoggedIn) {
                    onLoginSuccess()
                } else {
                    Toast.makeText(
                        context,
                        "Credenciales incorrectas.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
    }
}