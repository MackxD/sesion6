package com.example.conexionbd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var btnLogin : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText
    lateinit var tvHeader : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.idBtnLogin)
        etEmail = findViewById(R.id.idEdtUserName)
        etPassword = findViewById(R.id.idEdtPassword)
        tvHeader = findViewById(R.id.idTVHeader)


        btnLogin.setOnClickListener {

            val  apiService = ApiClient.getClient()

            val body : LoginRequest = LoginRequest(etEmail.text.toString(),etPassword.text.toString())

            val call = apiService.login(body)
            call.enqueue(object : Callback<LoginResponse> {

                override  fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ){
                    if (response.isSuccessful){
                        val loginResponse = response.body()
                        tvHeader.text = "Bienvenido" + loginResponse?.name
                        //Manejar el error de red
                        Toast.makeText(applicationContext,"Todo ok",Toast.LENGTH_LONG).show()
                    }else{
                        //Manejar error de red
                        Toast.makeText(applicationContext,"Error ok",Toast.LENGTH_LONG).show()
                    }

                }

                override  fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Manejar el error de red
                    Toast.makeText(applicationContext,"Error de red",Toast.LENGTH_LONG).show()

                }

            })

        }

    }
}