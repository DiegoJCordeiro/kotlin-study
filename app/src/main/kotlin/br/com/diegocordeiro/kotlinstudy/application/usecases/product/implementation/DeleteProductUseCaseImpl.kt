package br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProductDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.DeleteProductUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProductCRUDRepository

class DeleteProductUseCaseImpl(
    override val productMongoRepositoryDAO: MongoProductCRUDRepository
): DeleteProductUseCase {

    override fun execute(productDeleteInput: ProductDeleteInput): ProductDeleteOutput {
        val wasDeleted = productMongoRepositoryDAO.deleteOne(productDeleteInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return ProductDeleteOutput(
            id = productDeleteInput.id,
            wasDeleted = true
        )
    }
}