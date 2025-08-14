package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Professional

fun Professional.toCreateOutput(): ProfessionalCreateOutput = ProfessionalCreateOutput(
    id = this.id
)

fun Professional.toUpdateOutput(): ProfessionalUpdateOutput = ProfessionalUpdateOutput(
    id = this.id.orEmpty()

)