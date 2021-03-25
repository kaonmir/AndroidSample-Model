package xyz.kaonmir.model.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import xyz.kaonmir.model.dao.UnitDao

@Entity
data class Soldier(
//    val name: Name,
    @PrimaryKey val serialNumber: String,
    @Embedded val name: Name,
) {
    override fun toString(): String = "Name: $name, serial number: $serialNumber"
}