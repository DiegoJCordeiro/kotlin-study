package br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.CreateProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProfessionalCRUDRepository

class CreateProfessionalUseCaseImpl(
    override val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
): CreateProfessionalUseCase {

    override fun execute(professionalCreateInput: ProfessionalCreateInput): ProfessionalCreateOutput {

        val professionalCreated = professionalMongoRepositoryDAO.insertOne(
            professionalCreateInput.toEntity()
        )

        return professionalCreated.toCreateOutput()
    }
}