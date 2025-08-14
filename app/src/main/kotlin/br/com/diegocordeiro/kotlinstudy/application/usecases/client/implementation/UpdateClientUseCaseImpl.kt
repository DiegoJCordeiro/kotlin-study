package br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.client.UpdateClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoClientCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput

class UpdateClientUseCaseImpl(
    override val clientMongoRepositoryDAO: MongoClientCRUDRepository
): UpdateClientUseCase {

    override fun execute(clientInput: ClientUpdateInput): ClientUpdateOutput {

        val clientUpdated = clientMongoRepositoryDAO.updateOne(
            mapOf(
                "_id" to clientInput.id,
            ),
            clientInput.toEntity()
        )

        return clientUpdated.toUpdateOutput()
    }
}