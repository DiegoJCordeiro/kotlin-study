package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.AddressUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Address

fun Address.toCreateOutput(): AddressCreateOutput = AddressCreateOutput(
    id = this.id,
)

fun Address.toUpdateOutput(): AddressUpdateOutput = AddressUpdateOutput(
    id = this.id.orEmpty(),
)