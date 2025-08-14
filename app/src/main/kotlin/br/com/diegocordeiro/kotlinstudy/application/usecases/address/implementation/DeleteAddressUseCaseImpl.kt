package br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.address.DeleteAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.exceptions.ObjectWasNotDeletedException
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoAddressCRUDRepository

class DeleteAddressUseCaseImpl(
    override val mongoAddressCRUDRepository: MongoAddressCRUDRepository
): DeleteAddressUseCase {

    override fun execute(addressDeleteInput: AddressDeleteInput): AddressDeleteOutput {
        val wasDeleted = mongoAddressCRUDRepository.deleteOne(addressDeleteInput.id)

        if (!wasDeleted) {
            throw ObjectWasNotDeletedException("the object requested cannot be deleted")
        }

        return AddressDeleteOutput(addressDeleteInput.id, true)
    }
}