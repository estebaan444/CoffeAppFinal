package com.estebi.coffeappfinal

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.userName
import com.estebi.coffeappfinal.databases.CoffeShopDao
import com.estebi.coffeappfinal.databinding.ActivityLoginBinding
import com.estebi.coffeappfinal.model.Coffes
import com.estebi.coffeappfinal.model.History
import com.estebi.coffeappfinal.model.Pastes
import com.estebi.coffeappfinal.repositori.Repositori
import com.estebi.coffeappfinal.repositori.Repositori.Companion.history
import com.estebi.coffeappfinal.viewModels.LoginViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginButton.setOnClickListener {
            var email = binding.editTextUser.text.toString()
            var password = binding.editTextTextPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,"Siusplau emplena els camps", Toast.LENGTH_SHORT).show()
            }else{
                loginViewModel.getVerify(this, email, password )?.observe(this,Observer{ userList->
                    if (userList.isEmpty()){
                        Toast.makeText(this,"Usuari innexistent", Toast.LENGTH_SHORT).show()
                    }else{
                        populateDatabase()
                        Intent(this, MainUi::class.java).also {
                            startActivity(it)
                            userName = email
                        }
                        Toast.makeText(this,"Benvingut $userName", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })
            }
        }

        binding.goToRegisterButton.setOnClickListener {
            Intent(this, SignUp::class.java).also {
                startActivity(it)
            }
        }
    }
    private fun populateDatabase() {
        lifecycleScope.launch {
            Repositori.deleteCoffes(this@Login)
            Repositori.deletePastes(this@Login)
            Repositori.insertCoffe(this@Login, Coffes("Cafe con leche", 2))
            Repositori.insertCoffe(this@Login, Coffes("Cafe cortado", 2))
            Repositori.insertCoffe(this@Login, Coffes("Cafe irlandes", 1))
            Repositori.insertCoffe(this@Login, Coffes("Cafe americano", 2))
            Repositori.insertCoffe(this@Login, Coffes("Cafe espresso", 1))
            Repositori.insertCoffe(this@Login, Coffes("Cafe solo", 1))
            Repositori.insertPasta(this@Login, Pastes("Tarta de queso", 2))
            Repositori.insertPasta(this@Login, Pastes("Tarta de chocolate", 1))
            Repositori.insertPasta(this@Login, Pastes("Tarta de zanahoria", 1))
            Repositori.insertPasta(this@Login, Pastes("Tarta de manzana", 1))
            Repositori.insertPasta(this@Login, Pastes("Croisant", 2))
            Repositori.insertPasta(this@Login, Pastes("Donut", 1))
        }
    }
}