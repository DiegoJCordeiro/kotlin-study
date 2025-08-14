package br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.CreateEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoEstablishmentCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput

class CreateEstablishmentUseCaseImpl(
    override val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository
): CreateEstablishmentUseCase {

    override fun execute(establishmentCreateInput: EstablishmentCreateInput): EstablishmentCreateOutput {

        val establishmentCreated = establishmentMongoRepositoryDAO.insertOne(
            establishmentCreateInput.toEntity()
        )

        return establishmentCreated.toCreateOutput()
    }
}