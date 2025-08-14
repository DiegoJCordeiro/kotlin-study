package br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.DeleteProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProfessionalCRUDRepository

class DeleteProfessionalUseCaseImpl(
    override val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
): DeleteProfessionalUseCase {

    override fun execute(professionalDeleteInput: ProfessionalDeleteInput): ProfessionalDeleteOutput {
        val wasDeleted = professionalMongoRepositoryDAO.deleteOne(professionalDeleteInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return ProfessionalDeleteOutput(
            id = professionalDeleteInput.id,
            wasDeleted = true
        )
    }
}