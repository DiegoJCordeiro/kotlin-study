package br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.GetProductUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProductCRUDRepository

class GetProductUseCaseImpl(
    override val productMongoRepositoryDAO: MongoProductCRUDRepository
): GetProductUseCase {

    override fun execute(id: String): List<ProductCreateOutput> {
        if (id.isBlank() || id.isEmpty()) {
            return productMongoRepositoryDAO.findAll()
                .map { it.toCreateOutput() }
        }

        return productMongoRepositoryDAO.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }
}