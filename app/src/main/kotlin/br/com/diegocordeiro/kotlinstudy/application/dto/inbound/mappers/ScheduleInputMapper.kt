package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.mappers

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleUpdateInput
import br.com.diegocordeiro.kotlinstudy.domain.models.Schedule
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun ScheduleCreateInput.toEntity(): Schedule = toSchedule(
    id = this.id ?: ObjectId.get().toHexString(),
    professional = this.professional,
    establishment = this.establishment,
    products = this.products,
    dateTimeSchedule = this.dateTimeSchedule,
    createdAt = this.createdAt
)

fun ScheduleUpdateInput.toEntity(): Schedule = toSchedule(
    id = this.id,
    professional = this.professional,
    establishment = this.establishment,
    products = this.products,
    dateTimeSchedule = this.dateTimeSchedule,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

private fun toSchedule(
    id: String,
    professional: String,
    establishment: String,
    products: List<String>,
    dateTimeSchedule: LocalDateTime?,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime? = null
): Schedule = Schedule(
    id = id,
    professional = professional,
    establishment = establishment,
    products = products,
    dateTimeSchedule = dateTimeSchedule ?: LocalDateTime.now(),
    createdAt = createdAt,
    updatedAt = updatedAt
)