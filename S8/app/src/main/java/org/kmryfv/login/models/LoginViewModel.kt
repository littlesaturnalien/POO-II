package org.kmryfv.login.models

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var isLoggedIn : Boolean = false
        private set

    fun login(username : String, password : String) {
        if (username == "admin" && password == "password") {
            isLoggedIn = true
        } else {
            isLoggedIn = false
        }
    }
}