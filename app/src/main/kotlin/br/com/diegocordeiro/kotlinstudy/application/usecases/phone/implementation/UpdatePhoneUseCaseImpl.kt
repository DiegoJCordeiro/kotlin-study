package br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.UpdatePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPhoneCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput

class UpdatePhoneUseCaseImpl(
    override val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository
): UpdatePhoneUseCase {

    override fun execute(phoneUpdateInput: PhoneUpdateInput): PhoneUpdateOutput {
        val phoneUpdated = phoneMongoRepositoryDAO.updateOne(
            mapOf(
                "_id" to phoneUpdateInput.id,
            ),
            phoneUpdateInput.toEntity()
        )

        return phoneUpdated.toUpdateOutput()
    }
}