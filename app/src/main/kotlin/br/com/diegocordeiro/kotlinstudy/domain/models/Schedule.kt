package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Schedule(
    @SerialName("_id")
    val id: String?,
    @SerialName("professional")
    val professional: String,
    @SerialName("establishment")
    val establishment: String,
    @SerialName("products")
    val products: List<String>,
    @SerialName("dateTimeSchedule")
    @Serializable(with = LocalDateTimeSerializer::class)
    val dateTimeSchedule: LocalDateTime,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)