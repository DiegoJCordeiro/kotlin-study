package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class PlanCreateOutput(
    val id: String?
)

@Serializable
data class PlanUpdateOutput(
    val id: String
)

@Serializable
data class PlanDeleteOutput(
    val id: String,
    val wasDeleted: Boolean
)