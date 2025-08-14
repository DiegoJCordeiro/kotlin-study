package br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.client.CreateClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientCreateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoClientCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput

class CreateClientUseCaseImpl(
    override val clientMongoRepositoryDAO: MongoClientCRUDRepository
): CreateClientUseCase {

    override fun execute(clientInput: ClientCreateInput): ClientCreateOutput {

        val clientCreated = clientMongoRepositoryDAO.insertOne(
            clientInput.toEntity()
        )

        return clientCreated.toCreateOutput()
    }
}