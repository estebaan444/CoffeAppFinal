package com.estebi.coffeappfinal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @ColumnInfo(name = "name")
    var userName: String,
    @ColumnInfo(name = "password")
    var userPassword: String,
) {
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}