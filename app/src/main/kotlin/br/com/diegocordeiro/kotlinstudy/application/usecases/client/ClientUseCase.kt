package br.com.diegocordeiro.kotlinstudy.application.usecases.client

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoClientCRUDRepository

interface GetClientUseCase {

    val clientMongoRepositoryDAO: MongoClientCRUDRepository
    fun execute(id: String = ""): List<ClientCreateOutput>
}

interface CreateClientUseCase {

    val clientMongoRepositoryDAO: MongoClientCRUDRepository

    fun execute(clientInput: ClientCreateInput): ClientCreateOutput
}

interface UpdateClientUseCase {

    val clientMongoRepositoryDAO: MongoClientCRUDRepository

    fun execute(clientInput: ClientUpdateInput): ClientUpdateOutput
}

interface DeleteClientUseCase {

    val clientMongoRepositoryDAO: MongoClientCRUDRepository

    fun execute(clientInput: ClientDeleteInput): ClientDeleteOutput
}