package com.estebi.coffeappfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.estebi.coffeappfinal.databinding.ActivitySignUpBinding
import com.estebi.coffeappfinal.model.User
import com.estebi.coffeappfinal.repositori.Repositori

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.regiterButtonReg.setOnClickListener {
            val email = binding.editTextUserReg.text.toString()
            val password = binding.editTextTextPasswordReg.text.toString()
            val password2 = binding.editTextTextPassword2.text.toString()
            if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this,"Siusplau introdueix tots els camps", Toast.LENGTH_SHORT).show()
            }else if(password != password2){
                Toast.makeText(this,"Les contrasenyes no coincideixen!", Toast.LENGTH_SHORT).show()
            }
            else{
                Repositori.insertUser(this ,User(email, password))
                Toast.makeText(this,"Registrat exitosament!", Toast.LENGTH_SHORT).show()
                Intent(this, Login::class.java).also {
                    startActivity(it)
                }
                finish()
            }
        }

        binding.goToLoginButtonReg.setOnClickListener {
            Intent(this, Login::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }
}