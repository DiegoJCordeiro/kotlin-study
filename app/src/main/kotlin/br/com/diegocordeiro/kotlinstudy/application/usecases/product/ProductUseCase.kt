package br.com.diegocordeiro.kotlinstudy.application.usecases.product

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductCreateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProductCRUDRepository

interface GetProductUseCase {
    val productMongoRepositoryDAO: MongoProductCRUDRepository
    fun execute(id: String = ""): List<ProductCreateOutput>
}

interface CreateProductUseCase {
    val productMongoRepositoryDAO: MongoProductCRUDRepository
    fun execute(productCreateInput: ProductCreateInput): ProductCreateOutput
}

interface UpdateProductUseCase {
    val productMongoRepositoryDAO: MongoProductCRUDRepository
    fun execute(productUpdateInput: ProductUpdateInput): ProductUpdateOutput
}

interface DeleteProductUseCase {
    val productMongoRepositoryDAO: MongoProductCRUDRepository
    fun execute(productDeleteInput: ProductDeleteInput): ProductDeleteOutput
}