package br.com.diegocordeiro.kotlinstudy.application.usecases.plan

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanDeleteOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPlanCRUDRepository


interface GetPlanUseCase{
    val planMongoRepositoryDAO: MongoPlanCRUDRepository
    fun execute(id: String = ""): List<PlanCreateOutput>
}

interface CreatePlanUseCase {
    val planMongoRepositoryDAO: MongoPlanCRUDRepository
    fun execute(planCreateInput: PlanCreateInput): PlanCreateOutput
}

interface UpdatePlanUseCase {
    val planMongoRepositoryDAO: MongoPlanCRUDRepository
    fun execute(planUpdateInput: PlanUpdateInput): PlanUpdateOutput
}

interface DeletePlanUseCase {
    val planMongoRepositoryDAO: MongoPlanCRUDRepository
    fun execute(planDeleteInput: PlanDeleteInput): PlanDeleteOutput
}