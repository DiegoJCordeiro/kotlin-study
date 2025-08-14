package br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPlanCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.UpdatePlanUseCase

class UpdatePlanUseCaseImpl(
    override val planMongoRepositoryDAO: MongoPlanCRUDRepository
): UpdatePlanUseCase {

    override fun execute(planUpdateInput: PlanUpdateInput): PlanUpdateOutput {
        val planUpdated = planMongoRepositoryDAO.updateOne(
            mapOf(
                "_id" to planUpdateInput.id,
            ),
            planUpdateInput.toEntity()
        )

        return planUpdated.toUpdateOutput()
    }
}