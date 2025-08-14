package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.enums.ProductType
import br.com.diegocordeiro.kotlinstudy.domain.models.Product
import org.bson.types.ObjectId
import java.math.BigDecimal
import java.time.LocalDateTime

fun ProductCreateInput.toEntity(): Product = toProduct(
    id = this.id ?: ObjectId.get().toHexString(),
    name = this.name,
    description = this.description,
    price = this.price,
    remainingTime = this.remainingTime,
    blockedTime = this.blockedTime,
    productType = ProductType.entries.first { this.productType.value == it.value },
    createdAt = this.createdAt
)

fun ProductUpdateInput.toEntity(): Product = toProduct(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    remainingTime = this.remainingTime,
    blockedTime = this.blockedTime,
    productType = ProductType.entries.first { this.productType.value == it.value },
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

private fun toProduct(
    id: String,
    name: String,
    description: String,
    price: BigDecimal,
    remainingTime: Int,
    blockedTime: Boolean,
    productType: ProductType,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime? = null
): Product = Product(
    id = id,
    name = name,
    description = description,
    price = price,
    remainingTime = remainingTime,
    blockedTime = blockedTime,
    productType = productType,
    createdAt = createdAt,
    updatedAt = updatedAt
)