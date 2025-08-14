package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.models.Establishment
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun EstablishmentCreateInput.toEntity(): Establishment = toEstablishment(
    id = this.id ?: ObjectId.get().toHexString(),
    name = this.name,
    description = this.description,
    status = StatusType.entries.first { this.status.value == it.value },
    addresses = this.addresses,
    professionals = this.professionals,
    createdAt = LocalDateTime.now(),
    updatedAt = null
)

fun EstablishmentUpdateInput.toEntity(): Establishment = toEstablishment(
    id = this.id,
    name = this.name,
    description = this.description,
    status = StatusType.entries.first { this.status.value == it.value },
    addresses = this.addresses,
    professionals = this.professionals,
    createdAt = this.createdAt,
    updatedAt = LocalDateTime.now()
)

fun toEstablishment(
    id: String?,
    name: String,
    description: String,
    status: StatusType,
    addresses: List<String>,
    professionals: List<String>,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
): Establishment = Establishment(
    id = id,
    name = name,
    description = description,
    statusType = status,
    addresses = addresses,
    professionals = professionals,
    createdAt = createdAt,
    updatedAt = updatedAt
)