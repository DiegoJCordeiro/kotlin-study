package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ClientCreateInput(
    val id: String?,
    val fullName: String,
    val enabled: Boolean,
    val gender: String,
    val document: String,
    val email: String,
    val addresses: List<String>,
    val favorites: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?
)

@Serializable
data class ClientUpdateInput(
    val id: String,
    val fullName: String,
    val enabled: Boolean,
    val gender: String,
    val document: String,
    val email: String,
    val addresses: List<String>,
    val favorites: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)

@Serializable
data class ClientDeleteInput(
    val id: String
)
