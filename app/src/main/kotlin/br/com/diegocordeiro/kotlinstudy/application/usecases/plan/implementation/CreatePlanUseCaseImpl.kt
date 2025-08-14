package br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.CreatePlanUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPlanCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput

class CreatePlanUseCaseImpl(
    override val planMongoRepositoryDAO: MongoPlanCRUDRepository
): CreatePlanUseCase {

    override fun execute(planCreateInput: PlanCreateInput): PlanCreateOutput {

        val planCreated = planMongoRepositoryDAO.insertOne(
            planCreateInput.toEntity()
        )

        return planCreated.toCreateOutput()
    }
}