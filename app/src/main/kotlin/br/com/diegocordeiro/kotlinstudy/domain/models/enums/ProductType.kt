package br.com.diegocordeiro.kotlinstudy.domain.enums

enum class ProductType(
    val value: Boolean,
    val description: String,
) {
    SERVICE(false, "service"),
    PHYSIC_PRODUCT(true, "physic_product")
}