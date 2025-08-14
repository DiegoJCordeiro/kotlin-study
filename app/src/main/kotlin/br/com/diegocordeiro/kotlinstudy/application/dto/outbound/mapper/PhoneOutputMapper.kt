package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Phone

fun Phone.toCreateOutput(): PhoneCreateOutput = PhoneCreateOutput(
    id = this.id,
)

fun Phone.toUpdateOutput(): PhoneUpdateOutput = PhoneUpdateOutput(
    id = this.id.orEmpty(),
)