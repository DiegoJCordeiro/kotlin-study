package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Product
import br.com.diegocordeiro.kotlinstudy.domain.enums.ProductType
import java.math.BigDecimal

fun createProduct(): Product = Product(
    id = "id-test-bson",
    name = "name-test-bson",
    description = "description-test-bson",
    price = BigDecimal.ZERO,
    remainingTime = 1,
    blockedTime = false,
    productType = ProductType.PHYSIC_PRODUCT
)

fun createProductList(): List<Product> = listOf(
    createProduct(),
)