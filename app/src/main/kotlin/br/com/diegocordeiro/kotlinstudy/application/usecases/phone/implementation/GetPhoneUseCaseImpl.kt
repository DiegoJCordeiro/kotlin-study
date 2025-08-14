package br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPhoneCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.GetPhoneUseCase

class GetPhoneUseCaseImpl(
    override val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository
): GetPhoneUseCase {

    override fun execute(id: String): List<PhoneCreateOutput> {
        if (id.isBlank() || id.isEmpty()) {
            return phoneMongoRepositoryDAO.findAll()
                .map { it.toCreateOutput() }
        }

        return phoneMongoRepositoryDAO.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }
}