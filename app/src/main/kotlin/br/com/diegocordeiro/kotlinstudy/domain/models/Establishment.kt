package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Establishment(
    @SerialName("_id")
    val id: String?,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("status")
    val statusType: StatusType,
    @SerialName("address")
    val addresses: List<String>,
    @SerialName("professionals")
    val professionals: List<String>,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)