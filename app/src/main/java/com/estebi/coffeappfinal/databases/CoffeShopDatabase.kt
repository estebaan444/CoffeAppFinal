package com.estebi.coffeappfinal.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.estebi.coffeappfinal.model.User
import com.estebi.coffeappfinal.model.Coffes
import com.estebi.coffeappfinal.model.History
import com.estebi.coffeappfinal.model.Pastes

@Database(
    entities = [User::class, Coffes::class, Pastes::class, History::class],
    version = 2,
    exportSchema = false
)


abstract class CoffeShopDatabase  : RoomDatabase(){
    abstract fun coffeShopDao() : CoffeShopDao

    companion object {
        @Volatile
        private var INSTANCE: CoffeShopDatabase? = null
        fun getDatabase(context: Context): CoffeShopDatabase {
            // Delete all content here.
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): CoffeShopDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CoffeShopDatabase::class.java,
                "bar_databae"
            )
                .build()
        }
    }
}

