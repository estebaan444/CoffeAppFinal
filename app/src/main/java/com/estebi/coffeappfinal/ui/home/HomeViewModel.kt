package com.estebi.coffeappfinal.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.userName

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Benvingut $userName!"
    }
    val text: LiveData<String> = _text
}