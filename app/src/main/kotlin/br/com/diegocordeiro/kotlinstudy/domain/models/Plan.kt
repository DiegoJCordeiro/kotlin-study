package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import br.com.diegocordeiro.kotlinstudy.config.serializer.BigDecimalSerializer
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class Plan(
    @SerialName("_id")
    val id: String?,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String?,
    @SerialName("price")
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    @SerialName("status")
    val statusType: StatusType,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)