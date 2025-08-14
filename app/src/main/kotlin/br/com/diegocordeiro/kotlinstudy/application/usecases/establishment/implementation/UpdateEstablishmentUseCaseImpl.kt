package br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.UpdateEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoEstablishmentCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput

class UpdateEstablishmentUseCaseImpl(
    override val establishmentMongoRepositoryDAO: MongoEstablishmentCRUDRepository
): UpdateEstablishmentUseCase {

    override fun execute(establishmentUpdateInput: EstablishmentUpdateInput): EstablishmentUpdateOutput {
        val establishmentUpdated = establishmentMongoRepositoryDAO.updateOne(
            mapOf(
                "_id" to establishmentUpdateInput.id,
            ),
            establishmentUpdateInput.toEntity()
        )

        return establishmentUpdated.toUpdateOutput()
    }
}