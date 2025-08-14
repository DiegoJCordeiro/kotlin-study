package br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.CreateScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoScheduleCRUDRepository

class CreateScheduleUseCaseImpl(
    override val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
): CreateScheduleUseCase {

    override fun execute(scheduleCreateInput: ScheduleCreateInput): ScheduleCreateOutput {

        val scheduleCreated = scheduleMongoRepositoryDAO.insertOne(
            scheduleCreateInput.toEntity()
        )

        return scheduleCreated.toCreateOutput()
    }
}