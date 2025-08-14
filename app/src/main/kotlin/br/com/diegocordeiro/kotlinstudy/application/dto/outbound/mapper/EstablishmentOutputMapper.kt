package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.EstablishmentUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Establishment

fun Establishment.toCreateOutput(): EstablishmentCreateOutput = EstablishmentCreateOutput(
    id = this.id,
)

fun Establishment.toUpdateOutput(): EstablishmentUpdateOutput = EstablishmentUpdateOutput(
    id = this.id.orEmpty(),
)
