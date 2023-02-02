package com.estebi.coffeappfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.estebi.coffeappfinal.databases.CoffeShopDatabase
import com.estebi.coffeappfinal.model.Coffes
import com.estebi.coffeappfinal.repositori.Repositori
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        startTimer()
    }
    private fun startTimer() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, Login::class.java).apply {}
                startActivity(intent)
                finish()
            }
        }.start()
    }
}