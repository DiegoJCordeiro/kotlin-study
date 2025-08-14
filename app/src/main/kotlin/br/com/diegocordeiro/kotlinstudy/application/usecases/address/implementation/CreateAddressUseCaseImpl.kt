package br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.address.CreateAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers.toEntity
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoAddressCRUDRepository

class CreateAddressUseCaseImpl(
    override val mongoAddressCRUDRepository: MongoAddressCRUDRepository
): CreateAddressUseCase {

    override fun execute(addressCreateInput: AddressCreateInput): AddressCreateOutput {

        val addressInserted = mongoAddressCRUDRepository.insertOne(addressCreateInput.toEntity())

        return addressInserted.toCreateOutput()
    }
}