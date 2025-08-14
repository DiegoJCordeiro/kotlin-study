package br.com.diegocordeiro.kotlinstudy.application.usecases.establishment

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentDeleteOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoEstablishmentCRUDRepository

interface GetEstablishmentUseCase {

    val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository

    fun execute(id: String = ""): List<EstablishmentCreateOutput>
}

interface CreateEstablishmentUseCase {

    val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository

    fun execute(establishmentCreateInput: EstablishmentCreateInput): EstablishmentCreateOutput
}

interface UpdateEstablishmentUseCase {

    val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository

    fun execute(establishmentUpdateInput: EstablishmentUpdateInput): EstablishmentUpdateOutput
}

interface DeleteEstablishmentUseCase {

    val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository

    fun execute(establishmentDeleteInput: EstablishmentDeleteInput): EstablishmentDeleteOutput
}