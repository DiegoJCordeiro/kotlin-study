package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.enums.PhoneType
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import br.com.diegocordeiro.kotlinstudy.domain.models.Phone
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun PhoneCreateInput.toEntity(): Phone = toPhone(
    id = this.id ?: ObjectId.get().toHexString(),
    countryCode = this.countryCode,
    ddd = this.ddd,
    phoneNumber = this.phoneNumber,
    description = this.description,
    statusType = StatusType.entries.first { this.status.value == it.value },
    phoneType = PhoneType.entries.first { this.phoneType.value == it.value },
    createdAt = LocalDateTime.now()
)

fun PhoneUpdateInput.toEntity(): Phone = toPhone(
    id = this.id,
    countryCode = this.countryCode,
    ddd = this.ddd,
    phoneNumber = this.phoneNumber,
    description = this.description,
    statusType = StatusType.entries.first { this.status.value == it.value },
    phoneType = PhoneType.entries.first { this.phoneType.value == it.value },
    createdAt = this.createdAt,
    updatedAt = LocalDateTime.now()
)

fun toPhone(
    id: String,
    countryCode: String,
    ddd: String,
    phoneNumber: String,
    description: String,
    phoneType: PhoneType,
    statusType: StatusType,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime? = null
): Phone = Phone(
    id = id,
    countryCode = countryCode,
    ddd = ddd,
    phoneNumber = phoneNumber,
    description = description,
    phoneType = phoneType,
    statusType = statusType,
    createdAt = createdAt,
    updatedAt = updatedAt
)