package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ScheduleCreateInput(
    val id: String?,
    val client: String,
    val professional: String,
    val establishment: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val dateTimeSchedule: LocalDateTime,
    val products: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?
)

@Serializable
data class ScheduleUpdateInput(
    val id: String,
    val client: String,
    val professional: String,
    val establishment: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val dateTimeSchedule: LocalDateTime,
    val products: List<String>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)

@Serializable
data class ScheduleDeleteInput(
    val id: String
)