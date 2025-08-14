package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Coupon (
    @SerialName("_id")
    val id: String,
    @SerialName("description")
    val description: String,
    @SerialName("establishments")
    val establishments: List<Establishment>,
    @SerialName("percentage")
    val percentage: Int,
    @SerialName("enabled")
    val enabled: StatusType,
    @SerialName("expiration")
    @Serializable(with = LocalDateTimeSerializer::class)
    val expiration: LocalDateTime,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)