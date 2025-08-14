package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.domain.enums.PhoneType
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Phone(
    @SerialName("_id")
    val id: String?,
    @SerialName("countryCode")
    val countryCode: String,
    @SerialName("ddd")
    val ddd: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("description")
    val description: String,
    @SerialName("status")
    val statusType: StatusType,
    @SerialName("phoneType")
    val phoneType: PhoneType,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)