package br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.CreatePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPhoneCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput

class CreatePhoneUseCaseImpl(
    override val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository
): CreatePhoneUseCase {

    override fun execute(phoneCreateInput: PhoneCreateInput): PhoneCreateOutput {

        val phoneCreated = phoneMongoRepositoryDAO.insertOne(
            phoneCreateInput.toEntity()
        )

        return phoneCreated.toCreateOutput()
    }
}