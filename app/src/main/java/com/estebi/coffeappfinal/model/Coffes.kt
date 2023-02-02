package com.estebi.coffeappfinal.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Coffes")
data class Coffes(
    @ColumnInfo(name = "CoffeName")
    var coffeName: String,
    @ColumnInfo(name = "CoffePrice")
    var coffePrice: Int
) {
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}