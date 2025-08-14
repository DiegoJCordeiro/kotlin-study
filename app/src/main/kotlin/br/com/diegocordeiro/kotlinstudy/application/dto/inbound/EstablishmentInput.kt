package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.Status
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class EstablishmentCreateInput(
    val id: String?,
    val name: String,
    val description: String,
    val status: Status,
    val addresses: List<String>,
    val professionals: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?
)

@Serializable
data class EstablishmentUpdateInput(
    val id: String,
    val name: String,
    val description: String,
    val status: Status,
    val addresses: List<String>,
    val professionals: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)

@Serializable
data class EstablishmentDeleteInput(
    val id: String
)