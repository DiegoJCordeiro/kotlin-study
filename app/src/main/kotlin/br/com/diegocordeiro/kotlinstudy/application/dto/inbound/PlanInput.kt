package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.Status
import br.com.diegocordeiro.kotlinstudy.config.serializer.BigDecimalSerializer
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class PlanCreateInput(
    val id: String?,
    val title: String,
    val description: String?,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val status: Status,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?
)

@Serializable
data class PlanUpdateInput(
    val id: String,
    val title: String,
    val description: String?,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val status: Status,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)

@Serializable
data class PlanDeleteInput(
    val id: String
)