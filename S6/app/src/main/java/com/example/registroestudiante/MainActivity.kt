package com.example.registroestudiante

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.registroestudiante.ui.theme.RegistroEstudianteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroEstudianteTheme {
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatosEstudiante(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Registro de Estudiante") }
            )
        },
        content = {padding ->
            Column(
                modifier = modifier
                    .fillMaxSize().
                    padding(padding)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = {nombre = it },
                    label = {Text("Nombre")},
                    modifier = Modifier.padding(8.dp)
                )
                OutlinedTextField(
                    value = apellido,
                    onValueChange = {apellido = it },
                    label = {Text("Apellido")},
                    modifier = Modifier.padding(8.dp)
                )
                OutlinedTextField(
                    value = carrera,
                    onValueChange = {carrera = it },
                    label = {Text("Carrera")},
                    modifier = Modifier.padding(8.dp)
                )
                Button(
                    onClick = {
                        val msn = "Nombre: $nombre, Apellido: $apellido, Carrera: $carrera"
                        Toast.makeText(context, msn, Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Registrar")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    RegistroEstudianteTheme {
        DatosEstudiante()
    }
}