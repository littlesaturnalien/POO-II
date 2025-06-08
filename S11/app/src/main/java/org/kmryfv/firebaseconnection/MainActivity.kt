package org.kmryfv.firebaseconnection

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import org.kmryfv.firebaseconnection.data.User
import org.kmryfv.firebaseconnection.ui.theme.FirebaseConnectionTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)

        setContent {
            FirebaseConnectionTheme {
                UserInterface()
            }
        }
    }
}

@Composable
fun UserInterface() {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val cif = remember { mutableStateOf("")}
    val userList = remember { mutableStateOf(listOf<User>()) }

    // State to control visibility of input fields
    val showInputFields = remember { mutableStateOf(false) }

    // Database reference
    val db = FirebaseDatabase.getInstance("https://conexionfirebase-b69f3-default-rtdb.firebaseio.com/").getReference("users")


    // Save data to Firebase
    fun saveUser() {
        val cifExistsQuery = db.orderByChild("cif").equalTo(cif.value)
        cifExistsQuery.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                // Si el CIF ya existe, no guardamos el usuario
                Log.d("Firebase", "Error: CIF already exists")
            } else {
                // Si el CIF no existe, guardamos el nuevo usuario
                val user = User(cif = cif.value, name = name.value.uppercase(), age = age.value.toIntOrNull() ?: 0)
                db.push().setValue(user).addOnSuccessListener {
                    Log.d("Firebase", "User data saved successfully!")
                }.addOnFailureListener { e ->
                    Log.e("Firebase", "Error saving user", e)
                }
            }
        }.addOnFailureListener { e ->
            Log.e("Firebase", "Error checking CIF", e)
        }
    }

    // Update data in Firebase
    fun updateUser(userId: String, updatedUser: User) {
        db.child(userId).setValue(updatedUser).addOnSuccessListener {
            Log.d("Firebase", "User data updated successfully!")
        }.addOnFailureListener { e ->
            Log.e("Firebase", "Error updating user", e)
        }
    }

    // Delete data from Firebase
    fun deleteUser(userId: String) {
        db.child(userId).removeValue().addOnSuccessListener {
            Log.d("Firebase", "User data deleted successfully!")
        }.addOnFailureListener { e ->
            Log.e("Firebase", "Error deleting user", e)
        }
    }

    // Fetch data from Firebase in real-time
    fun fetchUsers() {
        db.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                userList.value = snapshot.children.map {
                    it.getValue(User::class.java) ?: User()
                }
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                Log.e("Firebase", "Error fetching users", error.toException())
            }
        })
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Title
        Text("Enter User Information", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Button to toggle visibility of the input fields
        Button(onClick = { showInputFields.value = !showInputFields.value }) {
            Text(if (showInputFields.value) "Hide Fields" else "Show Fields")
        }

        // Show input fields if the state is true
        if (showInputFields.value) {
            // CIF Input Field
            InputField(value = cif.value, onValueChange = { cif.value = it }, label = "CIF")

            // Name Input Field
            InputField(value = name.value, onValueChange = { name.value = it }, label = "Name")

            // Age Input Field
            InputField(value = age.value, onValueChange = { age.value = it }, label = "Age")

            // Save and Fetch Buttons
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { saveUser() }) {
                    Text("Save User")
                }

                Button(onClick = { fetchUsers() }) {
                    Text("Fetch Users")
                }
            }
        }

        // Display real-time users from Firebase
        Spacer(modifier = Modifier.height(16.dp))
        Text("Users List", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
        // Wrap the user list inside LazyColumn to make it scrollable
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(userList.value) { user ->
                UserCard(user = user, onUpdate = { updateUser(user.cif, user) }, onDelete = { deleteUser(user.cif) })
            }
        }
    }
}

@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(label, fontSize = 16.sp, color = Color.Gray)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(Color.LightGray, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle IME action if needed */ })
        )
    }
}

@Composable
fun UserCard(user: User, onUpdate: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(4.dp) // This is the correct way to add elevation to a Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Cif: ${user.cif}", fontSize = 18.sp, color = Color.Blue)
            Text("Name: ${user.name}", fontSize = 18.sp)
            Text("Age: ${user.age}", fontSize = 16.sp)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = onUpdate) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}


