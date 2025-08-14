package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ScheduleUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Schedule

fun Schedule.toCreateOutput(): ScheduleCreateOutput = ScheduleCreateOutput(
    id = this.id
)

fun Schedule.toUpdateOutput(): ScheduleUpdateOutput = ScheduleUpdateOutput(
    id = this.id.orEmpty()
)