package br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.UpdateProductUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProductCRUDRepository

class UpdateProductUseCaseImpl(
    override val productMongoRepositoryDAO: MongoProductCRUDRepository
): UpdateProductUseCase {

    override fun execute(productUpdateInput: ProductUpdateInput): ProductUpdateOutput {
        val productUpdated = productMongoRepositoryDAO.updateOne(
            mapOf(
                "_id" to productUpdateInput.id,
            ),
            productUpdateInput.toEntity()
        )

        return productUpdated.toUpdateOutput()
    }
}