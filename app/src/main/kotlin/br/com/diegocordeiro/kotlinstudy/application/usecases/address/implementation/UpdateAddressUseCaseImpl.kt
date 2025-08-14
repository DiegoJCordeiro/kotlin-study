package br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.address.UpdateAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoAddressCRUDRepository

class UpdateAddressUseCaseImpl(
    override val mongoAddressCRUDRepository: MongoAddressCRUDRepository
): UpdateAddressUseCase {

    override fun execute(addressUpdateInput: AddressUpdateInput): AddressUpdateOutput {

        val addressUpdated = mongoAddressCRUDRepository.updateOne(
            mapOf(
                "_id" to addressUpdateInput.id,
            ),
            addressUpdateInput.toEntity()
        )

        return addressUpdated.toUpdateOutput()
    }
}