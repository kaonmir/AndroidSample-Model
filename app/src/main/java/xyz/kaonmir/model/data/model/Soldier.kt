package xyz.kaonmir.model.data.model

import androidx.room.*

/** Calling queries multi times is better than make extra @relation and @transaction */

@Entity(foreignKeys = [
    ForeignKey(entity = MilUnit::class,
            parentColumns = ["unit_id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.RESTRICT)
])
data class Soldier(
        @PrimaryKey
        @ColumnInfo(name = "soldier_id")
        val soldierId: String = "",
        @Embedded val name: Name,

        @ColumnInfo(name = "unit_id") val unit: String,
) {
    override fun toString(): String = "Name: $name, serial number: $soldierId"
}