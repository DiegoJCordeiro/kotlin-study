package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Phone
import br.com.diegocordeiro.kotlinstudy.domain.enums.PhoneType
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType

fun createPhone(): Phone = Phone(
    id = "id-test-bson",
    countryCode = "55",
    ddd = "011",
    phoneNumber = "99988-7766",
    description = "test-description-bson",
    statusType = StatusType.ENABLE,
    phoneType = PhoneType.CELLPHONE
)

fun createPhoneList(): List<Phone> = listOf(
    createPhone()
)