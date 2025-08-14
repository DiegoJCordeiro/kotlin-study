package br.com.diegocordeiro.kotlinstudy.domain.models

import br.com.diegocordeiro.kotlinstudy.domain.enums.ProductType
import br.com.diegocordeiro.kotlinstudy.config.serializer.BigDecimalSerializer
import br.com.diegocordeiro.kotlinstudy.config.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class Product(
    @SerialName("_id")
    val id: String?,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    @SerialName("remainingTime")
    val remainingTime: Int,
    @SerialName("blockedTime")
    val blockedTime: Boolean,
    @SerialName("productType")
    val productType: ProductType,
    @SerialName("createdAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    @SerialName("updatedAt")
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime? = null,
)