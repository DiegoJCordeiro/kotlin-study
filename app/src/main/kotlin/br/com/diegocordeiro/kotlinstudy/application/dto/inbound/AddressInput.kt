package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.AddressType
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class AddressCreateInput(
    val id: String?,
    val street: String,
    val city: String,
    val zipcode: String,
    val country: String,
    val number: String,
    val phones: List<String>,
    val type: AddressType,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime?
)

@Serializable
data class AddressUpdateInput(
    val id: String,
    val street: String,
    val city: String,
    val zipcode: String,
    val country: String,
    val number: String,
    val phones: List<String>,
    val type: AddressType,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime
)

data class AddressDeleteInput(
    val id: String
)