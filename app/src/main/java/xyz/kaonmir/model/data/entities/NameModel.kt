package xyz.kaonmir.model.data.entities

data class NameModel(
    val firstName: String,
    val middleName: String?,
    val lastName: String
    ) {
    override fun toString(): String = "$lastName, $firstName" + if (middleName != null) ". $middleName" else ""
}
