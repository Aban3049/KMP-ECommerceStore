package data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("creationAt")
    val creationAt: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("updatedAt")
    val updatedAt: String?
)