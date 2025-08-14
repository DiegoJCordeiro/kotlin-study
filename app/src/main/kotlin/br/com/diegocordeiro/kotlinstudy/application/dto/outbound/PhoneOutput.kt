package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

import kotlinx.serialization.Serializable

@Serializable
data class PhoneCreateOutput(
    val id: String?
)

@Serializable
data class PhoneUpdateOutput(
    val id: String
)

@Serializable
data class PhoneDeleteOutput(
    val id: String,
    val wasDeleted: Boolean
)