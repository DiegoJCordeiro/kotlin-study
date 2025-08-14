package br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.UpdateScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoScheduleCRUDRepository

class UpdateScheduleUseCaseImpl(
    override val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
): UpdateScheduleUseCase {

    override fun execute(scheduleUpdateInput: ScheduleUpdateInput): ScheduleUpdateOutput {
        val scheduleUpdated = scheduleMongoRepositoryDAO.updateOne(
            mapOf(
                "_id" to scheduleUpdateInput.id,
            ),
            scheduleUpdateInput.toEntity()
        )

        return scheduleUpdated.toUpdateOutput()
    }
}