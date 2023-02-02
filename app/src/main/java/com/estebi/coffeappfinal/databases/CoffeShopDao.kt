package com.estebi.coffeappfinal.databases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.estebi.coffeappfinal.model.Coffes
import com.estebi.coffeappfinal.model.History
import com.estebi.coffeappfinal.model.Pastes
import com.estebi.coffeappfinal.model.User

import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.userName


@Dao
interface CoffeShopDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCoffe(coffes: Coffes)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPasta(paste: Pastes)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addHistory(history: History)

    @Query("DELETE FROM coffes")
    fun deleteAll()

    @Query("DELETE FROM pastes")
    fun deleteAllPastes()

    @Query  ("SELECT * FROM coffes ORDER BY id DESC")
    suspend fun getAllCoffes(): List<Coffes>

    @Query  ("SELECT * FROM Pastes ORDER BY id DESC")
    suspend fun getAllPastes(): List<Pastes>

    @Query  ("SELECT * FROM history WHERE buyerName = :userName")
    suspend fun getHistoryByCurrentUser(userName: String): List<History>

    @Query("SELECT * FROM user WHERE name =:name AND password =:password")
    fun getAutentifyUser(name:String, password: String): LiveData<List<User>>
}