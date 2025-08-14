package br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation

import br.com.diegocordeiro.kotlinstudy.application.usecases.address.GetAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper.toCreateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoAddressCRUDRepository

class GetAddressUseCaseImpl(
    override val mongoAddressCRUDRepository: MongoAddressCRUDRepository
): GetAddressUseCase {

    override fun execute(id: String): List<AddressCreateOutput> {

        if (id.isBlank() || id.isEmpty()) {
            return mongoAddressCRUDRepository.findAll()
                .map { it.toCreateOutput() }
        }

        return mongoAddressCRUDRepository.findByID(id)
            ?.let { listOf(it.toCreateOutput()) }
            ?: emptyList()
    }
}