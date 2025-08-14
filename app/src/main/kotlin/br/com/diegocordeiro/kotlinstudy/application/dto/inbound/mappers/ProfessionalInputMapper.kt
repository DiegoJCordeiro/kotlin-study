package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.models.Professional
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun ProfessionalCreateInput.toEntity(): Professional = toProfessional(
    id = this.id ?: ObjectId.get().toHexString(),
    fullName = this.fullName,
    description = this.description,
    statusType = StatusType.entries.first { this.status.value == it.value },
    addresses = this.addresses,
    establishments = this.establishments,
    createdAt = LocalDateTime.now()
)

fun ProfessionalUpdateInput.toEntity(): Professional = toProfessional(
    id = id,
    fullName = this.fullName,
    description = this.description,
    statusType = StatusType.entries.first { this.status.value == it.value },
    addresses = this.addresses,
    establishments = this.establishments,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)

private fun toProfessional(
    id: String,
    fullName: String,
    description: String,
    statusType: StatusType,
    addresses: List<String>,
    establishments: List<String>,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime? = null
): Professional = Professional(
    id = id,
    fullName = fullName,
    description = description,
    statusType = statusType,
    addresses = addresses,
    establishments = establishments,
    createdAt = createdAt,
    updatedAt = updatedAt
)