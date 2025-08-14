package br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.DeleteScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoScheduleCRUDRepository

class DeleteScheduleUseCaseImpl(
    override val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
): DeleteScheduleUseCase {

    override fun execute(scheduleDeleteInput: ScheduleDeleteInput): ScheduleDeleteOutput {
        val wasDeleted = scheduleMongoRepositoryDAO.deleteOne(scheduleDeleteInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return ScheduleDeleteOutput(
            id = scheduleDeleteInput.id,
            wasDeleted = true
        )
    }
}