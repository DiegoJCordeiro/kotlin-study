package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Client
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import java.time.LocalDateTime

fun createClient(): Client = Client(
    id = "id-test-bson",
    fullName = "fullName-test",
    statusType = StatusType.ENABLE,
    gender = "gender-test",
    document = "document-test",
    email = "email-test",
    addresses = emptyList(),
    favorites = emptyList(),
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)

fun createClientList(): List<Client> = listOf(
    createClient()
)