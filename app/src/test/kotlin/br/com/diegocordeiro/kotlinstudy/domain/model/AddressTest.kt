package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Address
import br.com.diegocordeiro.kotlinstudy.domain.enums.AddressType
import java.time.LocalDateTime

fun createAddress(): Address = Address(
    id = "id-test-bson",
    street = "description-street-test",
    city = "description-city-test",
    zipcode =  "description-zipcode-test",
    country = "description-country-test",
    number = "1",
    phones = emptyList(),
    type = AddressType.MAIN,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now(),
)

fun createAddressList(): List<Address> = listOf(
    createAddress()
)