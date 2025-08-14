package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.domain.enums.AddressType
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Address (
    @SerialName("_id")
    val id: String?,
    @SerialName("street")
    val street: String,
    @SerialName("city")
    val city: String,
    @SerialName("zipcode")
    val zipcode: String,
    @SerialName("country")
    val country: String,
    @SerialName("number")
    val number: String,
    @SerialName("phones")
    val phones: List<String>,
    @SerialName("type")
    val type: AddressType,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
) {
}