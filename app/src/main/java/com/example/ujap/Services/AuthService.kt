package com.example.ujap.Services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ujap.Model.User
import com.example.ujap.Utilities.URL_LOGIN
import org.json.JSONException
import org.json.JSONObject

object AuthService {
    var token = ""
    var isLoggedIn = false

    fun logIn(context: Context, username: String, password: String, complete: (Boolean, VolleyError?) -> Unit) {
        val jsonBody = JSONObject()
        jsonBody.put("username", username)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val logInRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN, null, Response.Listener { response ->
            try {
                token = response.getString("token")
                val user = response.getJSONObject("usuario")
                UserDataService.id = user.getString("_id")
                UserDataService.nombre = user.getString("nombre")
                UserDataService.apellido = user.getString("apellido")
                UserDataService.username = user.getString("username")
                UserDataService.email = user.getString("email")
                UserDataService.semestre = user.getString("semestre")
                UserDataService.carrera = user.getString("carrera")
                UserDataService.cedula = user.getString("cedula")
                UserDataService.indice = user.getString("indice")
                isLoggedIn = true
                complete(true, null)
            } catch (e: JSONException) {
                Log.d("JSON", "EXC: ${e.localizedMessage}")
                complete(false, null)
            }
        }, Response.ErrorListener { error ->
            Log.d("LOGIN", "Error: $error")
            complete(false, error)
        }) {
            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        Volley.newRequestQueue(context).add(logInRequest)
    }

    fun logOut() {
        token = ""
        UserDataService.id = ""
        UserDataService.nombre = ""
        UserDataService.apellido = ""
        UserDataService.username = ""
        UserDataService.email = ""
        UserDataService.semestre = ""
        UserDataService.carrera = ""
        UserDataService.cedula = ""
        UserDataService.indice = ""
        isLoggedIn = false
    }
}