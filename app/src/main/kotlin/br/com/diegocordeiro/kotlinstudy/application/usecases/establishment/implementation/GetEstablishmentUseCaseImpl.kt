package br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoEstablishmentCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.GetEstablishmentUseCase

class GetEstablishmentUseCaseImpl(
    override val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository
) : GetEstablishmentUseCase {

    override fun execute(id: String): List<EstablishmentCreateOutput> {

        if (id.isBlank() || id.isEmpty()) {
            return establishmentMongoRepositoryDAO.findAll()
                .map { it.toCreateOutput() }
        }

        return establishmentMongoRepositoryDAO.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }
}