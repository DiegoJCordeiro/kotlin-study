package br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.UpdateProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProfessionalCRUDRepository

class UpdateProfessionalUseCaseImpl(
    override val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
): UpdateProfessionalUseCase {

    override fun execute(professionalUpdateInput: ProfessionalUpdateInput): ProfessionalUpdateOutput {
        val professionalUpdated = professionalMongoRepositoryDAO.updateOne(
            mapOf(
                "_id" to professionalUpdateInput.id,
            ),
            professionalUpdateInput.toEntity()
        )

        return professionalUpdated.toUpdateOutput()
    }
}