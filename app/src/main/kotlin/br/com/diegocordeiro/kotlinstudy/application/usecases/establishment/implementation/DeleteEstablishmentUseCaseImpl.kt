package br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.DeleteEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoEstablishmentCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException

class DeleteEstablishmentUseCaseImpl(
    override val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository
): DeleteEstablishmentUseCase {

    override fun execute(establishmentDeleteInput: EstablishmentDeleteInput): EstablishmentDeleteOutput {
        val wasDeleted = establishmentMongoRepositoryDAO.deleteOne(establishmentDeleteInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return EstablishmentDeleteOutput(
            id = establishmentDeleteInput.id,
            wasDeleted = true
        )
    }
}