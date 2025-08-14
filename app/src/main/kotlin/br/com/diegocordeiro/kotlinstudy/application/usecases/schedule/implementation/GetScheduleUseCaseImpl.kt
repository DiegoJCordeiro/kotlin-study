package br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.GetScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoScheduleCRUDRepository

class GetScheduleUseCaseImpl(
    override val scheduleMongoRepositoryDAO: MongoScheduleCRUDRepository
): GetScheduleUseCase {

    override fun execute(id: String): List<ScheduleCreateOutput> {

        if (id.isBlank() || id.isEmpty()) {
            return scheduleMongoRepositoryDAO.findAll()
                .map { it.toCreateOutput() }
        }

        return scheduleMongoRepositoryDAO.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }

}