package br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.client.DeleteClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientDeleteOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoClientCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException

class DeleteClientUseCaseImpl(
    override val clientMongoRepositoryDAO: MongoClientCRUDRepository
): DeleteClientUseCase {

    override fun execute(clientInput: ClientDeleteInput): ClientDeleteOutput {

        val wasDeleted = clientMongoRepositoryDAO.deleteOne(clientInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return ClientDeleteOutput(
            id = clientInput.id,
            wasDeleted = true
        )
    }
}