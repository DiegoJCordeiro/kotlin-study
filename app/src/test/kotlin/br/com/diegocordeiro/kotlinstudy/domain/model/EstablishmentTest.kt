package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Establishment
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import java.time.LocalDateTime

fun createEstablishment(): Establishment = Establishment(
    id = "id-test-bson",
    name = "name-test",
    description = "description-test",
    statusType = StatusType.ENABLE,
    addresses = emptyList(),
    professionals = emptyList(),
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)

fun createEstablishmentList(): List<Establishment> = listOf(
    createEstablishment()
)