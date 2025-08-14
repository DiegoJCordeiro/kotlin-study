package br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.GetProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProfessionalCRUDRepository

class GetProfessionalUseCaseImpl(
    override val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
): GetProfessionalUseCase {

    override fun execute(id: String): List<ProfessionalCreateOutput> {
        if (id.isBlank() || id.isEmpty()) {
            return professionalMongoRepositoryDAO.findAll()
                .map { it.toCreateOutput() }
        }

        return professionalMongoRepositoryDAO.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }
}