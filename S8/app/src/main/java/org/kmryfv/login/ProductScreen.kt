package org.kmryfv.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.kmryfv.login.models.Product
import org.kmryfv.login.models.ProductViewModel
import org.kmryfv.login.ui.theme.LoginTheme

class ProductScreen : ComponentActivity() {
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
fun ProductScreen(viewModel: ProductViewModel) {
    var cod by remember { mutableStateOf("")}
    var name by remember { mutableStateOf("")}
    var price by remember { mutableStateOf("")}
    var quantity by remember { mutableStateOf("")}
    val products by viewModel.products.collectAsState()

    Column(modifier= Modifier
        .fillMaxSize()
        .padding((16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = cod, onValueChange = { cod = it}, label = {Text("ID")})
        OutlinedTextField(value = name, onValueChange = { name = it}, label = {Text("Nombre")})
        OutlinedTextField(value = price, onValueChange = { price = it}, label = {Text("Precio")})
        OutlinedTextField(value = quantity, onValueChange = { quantity = it}, label = {Text("Cantidad")})
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val id = cod.toInt()
            val p = price.toDoubleOrNull()
            val c = quantity.toIntOrNull()
            if (!name.isBlank() && p!= null && c != null) {
                viewModel.addProduct(id, name, p, c)
                cod = ""
                name = ""
                price = ""
                quantity = ""
            }
        }) {
            Text("Agregar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Productos registrados: ${viewModel.listSize()}", style = MaterialTheme.typography.headlineSmall)
        ProductsList(products)
    }
}

@Composable
fun ProductsList(products: List<Product>) {
    LazyColumn {
        items(products) { product ->
            Card {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("ID: ${product.id}")
                    Text("Nombre: ${product.name}")
                    Text("Cantidad: ${product.quantity}")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}