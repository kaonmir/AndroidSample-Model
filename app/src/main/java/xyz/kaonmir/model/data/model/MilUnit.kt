package xyz.kaonmir.model.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Unit")
data class MilUnit(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "unit_id")
        val unitId: String = "",


)