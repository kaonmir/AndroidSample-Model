package xyz.kaonmir.model.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Unit(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "unit_id")
    val unitId: Int = 0,

    val name: String = "",
)