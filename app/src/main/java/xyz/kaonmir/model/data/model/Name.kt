package xyz.kaonmir.model.data.model

data class Name(
    val firstName: String,
    val middleName: String?,
    val lastName: String
    ) {
    override fun toString(): String = "$lastName, $firstName" + if (middleName != null) ". $middleName" else ""
}
