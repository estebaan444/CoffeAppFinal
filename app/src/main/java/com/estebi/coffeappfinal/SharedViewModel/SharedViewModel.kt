package com.estebi.coffeappfinal.SharedViewModel


class SharedViewModel {
    companion object{
        val listOfProduct: ArrayList<String> = ArrayList()
        val listOfPrices: ArrayList<Int> = ArrayList()
        fun sumTotal(): Int {
            return listOfPrices.sum()
        }
        var userName: String = ""
    }
}