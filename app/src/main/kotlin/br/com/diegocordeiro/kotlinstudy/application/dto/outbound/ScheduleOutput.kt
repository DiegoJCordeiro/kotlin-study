package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleCreateOutput(
    val id: String?
)

@Serializable
data class ScheduleUpdateOutput(
    val id: String
)

@Serializable
data class ScheduleDeleteOutput(
    val id: String,
    val wasDeleted: Boolean
)