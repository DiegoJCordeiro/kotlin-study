package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.models.Address
import br.com.diegocordeiro.kotlinstudy.domain.enums.AddressType
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun AddressCreateInput.toEntity(): Address =
    toAddress(
        id = this.id ?: ObjectId.get().toHexString(),
        street = this.street,
        city = this.city,
        zipcode = this.zipcode,
        country = this.country,
        number = this.number,
        phones = this.phones,
        isMain = this.type.isMain,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )

fun AddressUpdateInput.toEntity(): Address =
    toAddress(
        id = this.id,
        street = this.street,
        city = this.city,
        zipcode = this.zipcode,
        country = this.country,
        number = this.number,
        phones = this.phones,
        isMain = this.type.isMain,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )

private fun toAddress(
    id: String?,
    street: String,
    city: String,
    zipcode: String,
    country: String,
    number: String,
    phones: List<String> = emptyList(),
    isMain: Boolean,
    createdAt: LocalDateTime?,
    updatedAt: LocalDateTime?
): Address {
    return Address(
        id = id,
        street = street,
        city = city,
        zipcode = zipcode,
        country = country,
        number = number,
        phones = phones,
        type = if (isMain) AddressType.MAIN else AddressType.SECONDARY,
        createdAt = createdAt ?: LocalDateTime.now(),
        updatedAt = updatedAt
    )
}
