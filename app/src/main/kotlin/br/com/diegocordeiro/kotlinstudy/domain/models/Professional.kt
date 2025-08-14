package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Professional(
    @SerialName("_id")
    val id: String?,
    @SerialName("fullName")
    val fullName: String,
    @SerialName("description")
    val description: String,
    @SerialName("status")
    val statusType: StatusType,
    @SerialName("addresses")
    val addresses: List<String>,
    @SerialName("establishments")
    val establishments: List<String>,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)