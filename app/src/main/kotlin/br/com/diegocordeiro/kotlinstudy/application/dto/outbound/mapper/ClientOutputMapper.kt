package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ClientUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Client

fun Client.toCreateOutput(): ClientCreateOutput = ClientCreateOutput(
    id = this.id,
)

fun Client.toUpdateOutput(): ClientUpdateOutput = ClientUpdateOutput(
    id = this.id.orEmpty(),
)