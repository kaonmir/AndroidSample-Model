package xyz.kaonmir.model.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity
data class Soldier(
//    val name: Name,
        @PrimaryKey val serialNumber: String = "",
        @Embedded val name: Name,
//    @ColumnInfo(name = "unit_id") val unitId: Int = 0,
) {
    override fun toString(): String = "Name: $name, serial number: $serialNumber"
}