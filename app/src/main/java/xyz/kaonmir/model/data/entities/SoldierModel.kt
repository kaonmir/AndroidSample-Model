package xyz.kaonmir.model.data.entities

import androidx.room.*

/** Calling queries multi times is better than make extra @relation and @transaction */
// todo(1:M relation)

@Entity(
    tableName = "soldier",
//    foreignKeys = [
//        ForeignKey(entity = UnitModel::class,
//            parentColumns = ["unit_id"],
//            childColumns = ["unit_id"],
//            onDelete = ForeignKey.RESTRICT)
//    ]
)

data class SoldierModel(
        @PrimaryKey
        @ColumnInfo(name = "soldier_id")
        val soldierId: String = "",
        @Embedded val name: NameModel,

//        @ColumnInfo(name = "unit_id") val unit: String,
) {
    override fun toString(): String = "Name: $name, serial number: $soldierId"
}