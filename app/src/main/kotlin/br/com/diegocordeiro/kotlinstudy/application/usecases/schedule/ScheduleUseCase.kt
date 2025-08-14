package br.com.diegocordeiro.kotlinstudy.application.usecases.schedule

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleCreateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoScheduleCRUDRepository

interface GetScheduleUseCase {
    val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
    fun execute(id: String = ""): List<ScheduleCreateOutput>
}

interface CreateScheduleUseCase {
    val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
    fun execute(scheduleCreateInput: ScheduleCreateInput): ScheduleCreateOutput
}

interface UpdateScheduleUseCase {
    val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
    fun execute(scheduleUpdateInput: ScheduleUpdateInput): ScheduleUpdateOutput
}

interface DeleteScheduleUseCase {
    val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
    fun execute(scheduleDeleteInput: ScheduleDeleteInput): ScheduleDeleteOutput
}