package xyz.kaonmir.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Soldier(
//    @PrimaryKey(autoGenerate = true) val sid: Int = 0,
//    val name: Name,
    @PrimaryKey val serialNumber: String,
    val name: String,
) {
    override fun toString(): String = "Name: $name, serial number: $serialNumber"
}