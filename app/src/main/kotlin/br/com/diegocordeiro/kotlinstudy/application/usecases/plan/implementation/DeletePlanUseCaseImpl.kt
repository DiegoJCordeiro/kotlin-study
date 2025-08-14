package br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.DeletePlanUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPlanCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException

class DeletePlanUseCaseImpl(
    override val planMongoRepositoryDAO: MongoPlanCRUDRepository
): DeletePlanUseCase {

    override fun execute(planDeleteInput: PlanDeleteInput): PlanDeleteOutput {
        val wasDeleted = planMongoRepositoryDAO.deleteOne(planDeleteInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return PlanDeleteOutput(
            id = planDeleteInput.id,
            wasDeleted = true
        )
    }
}