package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Professional
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType

fun createProfessional(): Professional = Professional(
    id = "id-test-bson",
    fullName = "fullName-test",
    description = "description-test-bson",
    statusType = StatusType.ENABLE,
    establishments = emptyList(),
    addresses = emptyList()
)

fun createProfessionalList(): List<Professional> = listOf(
    createProfessional(),
)