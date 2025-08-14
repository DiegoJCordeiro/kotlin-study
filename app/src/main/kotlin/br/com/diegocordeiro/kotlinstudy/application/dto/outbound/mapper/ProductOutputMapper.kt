package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProductUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Product

fun Product.toCreateOutput() = ProductCreateOutput(
    id = this.id,
)

fun Product.toUpdateOutput() = ProductUpdateOutput(
    id = this.id.orEmpty(),
)