package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.PhoneType
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.Status
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PhoneCreateInput(
    val id: String?,
    val description: String,
    val countryCode: String,
    val ddd: String,
    val phoneNumber: String,
    val phoneType: PhoneType,
    val status: Status,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?
)

@Serializable
data class PhoneUpdateInput(
    val id: String,
    val description: String,
    val countryCode: String,
    val ddd: String,
    val phoneNumber: String,
    val phoneType: PhoneType,
    val status: Status,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)

@Serializable
data class PhoneDeleteInput(
    val id: String
)