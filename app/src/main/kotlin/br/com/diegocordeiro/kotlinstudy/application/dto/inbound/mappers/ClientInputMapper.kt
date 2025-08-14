package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.models.Client
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun ClientCreateInput.toEntity(): Client = toClient(
    id = this.id ?: ObjectId.get().toHexString(),
    fullName = this.fullName,
    statusType = StatusType.entries.first { this.enabled == it.value },
    gender = this.gender,
    document = this.document,
    email = this.email,
    address = this.addresses,
    favorites = this.favorites,
    createdAt = LocalDateTime.now(),
    updatedAt = null
)

fun ClientUpdateInput.toEntity(): Client = toClient(
    id = this.id ,
    fullName = this.fullName,
    statusType = StatusType.entries.first { this.enabled == it.value },
    gender = this.gender,
    document = this.document,
    email = this.email,
    address = this.addresses,
    favorites = this.favorites,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)

private fun toClient(
    id: String,
    fullName: String,
    statusType: StatusType,
    gender: String,
    document: String,
    email: String,
    address: List<String>,
    favorites: List<String>,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
): Client = Client(
    id = id,
    fullName = fullName,
    statusType = statusType,
    gender = gender,
    document = document,
    email = email,
    addresses = address,
    favorites = favorites,
    createdAt = createdAt,
    updatedAt = updatedAt
)