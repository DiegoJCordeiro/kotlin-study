package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PlanUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.models.Plan
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import org.bson.types.ObjectId
import java.math.BigDecimal
import java.time.LocalDateTime

fun PlanCreateInput.toEntity(): Plan = toPlan(
    id = this.id ?: ObjectId.get().toHexString(),
    title = this.title,
    description = this.description.orEmpty(),
    price = this.price,
    statusType = StatusType.entries.first { this.status.value == it.value },
    createdAt = this.createdAt,
)

fun PlanUpdateInput.toEntity(): Plan = toPlan(
    id = this.id,
    title = this.title,
    description = this.description.orEmpty(),
    price = this.price,
    statusType = StatusType.entries.first { this.status.value == it.value },
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun toPlan(
    id: String,
    title: String,
    description: String,
    price: BigDecimal,
    statusType: StatusType,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime? = null
): Plan = Plan(
    id = id,
    title = title,
    description = description,
    price = price,
    statusType = statusType,
    createdAt = createdAt,
    updatedAt = updatedAt
)