package xyz.kaonmir.model.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// todo(TypeConverter)

@Entity(tableName = "Unit")
data class UnitModel(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "unit_id")
        val unitId: String = "",

        val name: String = "",
        val commander: SoldierModel?,
//        val supervisor: UnitModel?
)