package com.example.ujap.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ujap.R
import com.example.ujap.Services.AuthService
import com.example.ujap.Services.UserDataService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginSpinner.visibility = View.INVISIBLE
    }

    fun onLogin(view: View) {
        val username = loginUsernameTxt.text.toString()
        val password = loginPasswordTxt.text.toString()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            loading(true)
            AuthService.logIn(this, username, password) {complete, error ->
                if (complete) {
                    Toast.makeText(this, "Inicio de sesion exitoso. Bienvenido ${UserDataService.nombre}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    if (error?.networkResponse?.statusCode == 401) {
                        Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Ha ocurrido un error. Por favor vuelva a intentarlo", Toast.LENGTH_SHORT).show()
                    }
                    loading(false)
                }
            }
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun loading(state: Boolean) {
        if (state) {
            loginSpinner.visibility = View.VISIBLE
        } else {
            loginSpinner.visibility = View.INVISIBLE
        }
        loginUsernameTxt.isEnabled = !state
        loginPasswordTxt.isEnabled = !state
        loginLoginBtn.isEnabled = !state
    }
}
