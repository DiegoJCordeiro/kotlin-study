package br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.client.GetClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientCreateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoClientCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput

class GetClientUseCaseImpl(
    override val clientMongoRepositoryDAO: MongoClientCRUDRepository
): GetClientUseCase {

    override fun execute(id: String): List<ClientCreateOutput> {

        if (id.isBlank() || id.isEmpty()) {
            return clientMongoRepositoryDAO.findAll()
                .map { it.toCreateOutput() }
        }

        return clientMongoRepositoryDAO.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }
}