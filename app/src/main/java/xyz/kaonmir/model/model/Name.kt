package xyz.kaonmir.model.model

data class Name(
    val firstName: String,
    val middleName: String?,
    val lastName: String
    ) {
    override fun toString(): String = "$lastName, $firstName" + if (middleName != null) ". $middleName" else ""
}
