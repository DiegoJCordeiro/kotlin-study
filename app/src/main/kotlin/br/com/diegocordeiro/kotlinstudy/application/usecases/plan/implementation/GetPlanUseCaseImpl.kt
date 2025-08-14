package br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPlanCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.GetPlanUseCase

class GetPlanUseCaseImpl(
    override val planMongoRepositoryDAO: MongoPlanCRUDRepository
): GetPlanUseCase {

    override fun execute(id: String): List<PlanCreateOutput> {

        if (id.isBlank() || id.isEmpty()) {
            return planMongoRepositoryDAO.findAll()
                .map { it.toCreateOutput() }
        }

        return planMongoRepositoryDAO.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }
}