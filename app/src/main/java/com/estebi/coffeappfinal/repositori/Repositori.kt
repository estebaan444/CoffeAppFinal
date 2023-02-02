package com.estebi.coffeappfinal.repositori

import android.content.Context
import androidx.lifecycle.LiveData
import com.estebi.coffeappfinal.databases.CoffeShopDatabase
import com.estebi.coffeappfinal.model.Coffes
import com.estebi.coffeappfinal.model.History
import com.estebi.coffeappfinal.model.Pastes
import com.estebi.coffeappfinal.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repositori {
    companion object {
        var coffeShopDatabase: CoffeShopDatabase? = null

        var coffes: List<Coffes>? = null

        var user: LiveData<List<User>>? = null

        var history: List<History>? = null


        fun initializeDB(context: Context): CoffeShopDatabase {
            return CoffeShopDatabase.getDatabase(context)
        }

        //INSERT user
        fun insertUser(context: Context, user: User) {
            coffeShopDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                coffeShopDatabase!!.coffeShopDao().addUser(user)
            }
        }

        fun insertHistory(context: Context, history: History) {
            coffeShopDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                coffeShopDatabase!!.coffeShopDao().addHistory(history)
            }
        }

        //INSERT coffes for popuate
        fun insertCoffe(context: Context, coffes: Coffes) {
            coffeShopDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                coffeShopDatabase!!.coffeShopDao().addCoffe(coffes)
            }
        }

        fun insertPasta(context: Context, pastes: Pastes) {
            coffeShopDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                coffeShopDatabase!!.coffeShopDao().addPasta(pastes)
            }
        }

        fun deleteCoffes(context: Context) {
            coffeShopDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                coffeShopDatabase!!.coffeShopDao().deleteAll()
            }
        }

        fun deletePastes(context: Context) {
            coffeShopDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                coffeShopDatabase!!.coffeShopDao().deleteAllPastes()
            }
        }


        //SQL autentify user
        fun getAutentifyUser(
            context: Context,
            name: String,
            password: String
        ): LiveData<List<User>>? {
            coffeShopDatabase = initializeDB(context)
            user = coffeShopDatabase!!.coffeShopDao().getAutentifyUser(name, password)
            return user
        }
    }
}