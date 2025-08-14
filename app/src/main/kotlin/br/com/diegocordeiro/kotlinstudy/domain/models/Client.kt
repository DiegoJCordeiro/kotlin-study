package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Client(
    @SerialName("_id")
    val id: String?,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("enabled")
    val statusType: StatusType,
    @SerialName("gender")
    val gender: String,
    @SerialName("document")
    val document: String,
    @SerialName("email")
    val email: String,
    @SerialName("addresses")
    val addresses: List<String>,
    @SerialName("favorites")
    val favorites: List<String>,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null
)