package com.estebi.coffeappfinal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pastes")
data class Pastes(
    @ColumnInfo(name = "pastaName")
    var pastaName: String,
    @ColumnInfo(name = "pastaPrice")
    var pastaPrice: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}