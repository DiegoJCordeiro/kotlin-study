package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Status(
    val value: Boolean,
    val description: String
) {
    ENABLE(true, "It's Enable"),
    DISABLE(false, "It's Disable")
}