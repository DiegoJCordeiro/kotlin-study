package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class ProductCreateOutput(
    val id: String?
)

@Serializable
data class ProductUpdateOutput(
    val id: String
)

@Serializable
data class ProductDeleteOutput(
    val id: String,
    val wasDeleted: Boolean
)