package com.estebi.coffeappfinal.viewModels

import com.estebi.coffeappfinal.model.User
import com.estebi.coffeappfinal.repositori.Repositori
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel



class LoginViewModel : ViewModel() {
    var user: LiveData<List<User>>? = null

    fun getVerify(
        context: Context,
        name: String,
        password: String
    ): LiveData<List<User>>? {
        user = Repositori.getAutentifyUser(context, name, password)
        return user
    }
}