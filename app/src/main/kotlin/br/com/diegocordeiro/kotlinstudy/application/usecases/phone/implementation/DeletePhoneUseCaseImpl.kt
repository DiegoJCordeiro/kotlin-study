package br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.DeletePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPhoneCRUDRepository
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException

class DeletePhoneUseCaseImpl(
    override val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository
): DeletePhoneUseCase {

    override fun execute(phoneDeleteInput: PhoneDeleteInput): PhoneDeleteOutput {
        val wasDeleted = phoneMongoRepositoryDAO.deleteOne(phoneDeleteInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return PhoneDeleteOutput(
            id = phoneDeleteInput.id,
            wasDeleted = true
        )
    }
}