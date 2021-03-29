package xyz.kaonmir.model.data.model

import androidx.room.*

@Entity
data class Soldier(
        @PrimaryKey
        @ColumnInfo(name = "soldier_id")
        val soldierId: String = "",
        @Embedded val name: Name,
//    @ColumnInfo(name = "unit_id") val unitId: Int = 0,
) {
    override fun toString(): String = "Name: $name, serial number: $soldierId"
}