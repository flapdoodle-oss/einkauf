package de.flapdoodle.einkauf

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey @ColumnInfo(name = "id") val it: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "amount") val amount: Int=0,
    @ColumnInfo(name = "price") val unitPriceCent: Int=0
)