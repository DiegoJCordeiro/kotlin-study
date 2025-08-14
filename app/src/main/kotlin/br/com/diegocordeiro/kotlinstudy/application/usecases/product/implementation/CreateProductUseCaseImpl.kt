package br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.CreateProductUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProductCRUDRepository

class CreateProductUseCaseImpl(
    override val productMongoRepositoryDAO: MongoProductCRUDRepository
): CreateProductUseCase {

    override fun execute(productCreateInput: ProductCreateInput): ProductCreateOutput {

        val productCreated = productMongoRepositoryDAO.insertOne(
            productCreateInput.toEntity()
        )

        return productCreated.toCreateOutput()
    }
}