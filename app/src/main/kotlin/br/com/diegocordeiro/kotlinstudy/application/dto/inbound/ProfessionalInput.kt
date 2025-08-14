package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.Status
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ProfessionalCreateInput(
    val id: String?,
    val fullName: String,
    val description: String,
    val status: Status,
    val establishments: List<String>,
    val addresses: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?
)

@Serializable
data class ProfessionalUpdateInput(
    val id: String,
    val fullName: String,
    val description: String,
    val status: Status,
    val establishments: List<String>,
    val addresses: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)

@Serializable
data class ProfessionalDeleteInput(
    val id: String
)
