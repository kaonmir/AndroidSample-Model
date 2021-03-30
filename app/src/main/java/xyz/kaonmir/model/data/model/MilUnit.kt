package xyz.kaonmir.model.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// todo(TypeConverter)

@Entity(tableName = "Unit")
data class MilUnit(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "unit_id")
        val unitId: String = "",
        val name: String = "",
        val commander: Soldier?,

        val supervisor: MilUnit?
)