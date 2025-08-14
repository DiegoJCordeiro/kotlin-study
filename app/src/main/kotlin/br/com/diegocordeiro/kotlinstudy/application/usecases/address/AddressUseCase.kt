package br.com.diegocordeiro.kotlinstudy.application.usecases.address

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressDeleteOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoAddressCRUDRepository

interface GetAddressUseCase {
    val mongoAddressCRUDRepository: MongoAddressCRUDRepository
    fun execute(id: String = ""): List<AddressCreateOutput>
}

interface CreateAddressUseCase {
    val mongoAddressCRUDRepository: MongoAddressCRUDRepository
    fun execute(addressCreateInput: AddressCreateInput): AddressCreateOutput
}

interface UpdateAddressUseCase {
    val mongoAddressCRUDRepository: MongoAddressCRUDRepository
    fun execute(addressUpdateInput: AddressUpdateInput): AddressUpdateOutput
}

interface DeleteAddressUseCase{
    val mongoAddressCRUDRepository: MongoAddressCRUDRepository
    fun execute(addressDeleteInput: AddressDeleteInput): AddressDeleteOutput
}