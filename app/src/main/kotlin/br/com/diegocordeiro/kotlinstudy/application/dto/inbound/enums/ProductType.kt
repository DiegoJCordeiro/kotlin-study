package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums

import kotlinx.serialization.Serializable

@Serializable
enum class ProductType(
    val value: Boolean,
    val description: String,
) {
    SERVICE(false, "service"),
    PHYSIC_PRODUCT(true, "physic_product")
}