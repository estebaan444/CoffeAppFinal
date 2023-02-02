package com.estebi.coffeappfinal.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class History(
    @ColumnInfo(name = "buyerName")
    var buyerName: String,
    @ColumnInfo(name = "Date")
    var date: String,
    @ColumnInfo(name = "TotalPrice")
    var totalPrice: Int
) {
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}