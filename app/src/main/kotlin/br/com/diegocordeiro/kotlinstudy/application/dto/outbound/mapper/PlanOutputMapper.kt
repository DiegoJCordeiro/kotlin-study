package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PlanUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Plan

fun Plan.toCreateOutput(): PlanCreateOutput = PlanCreateOutput(
    id = this.id,
)

fun Plan.toUpdateOutput(): PlanUpdateOutput = PlanUpdateOutput(
    id = this.id.orEmpty(),
)