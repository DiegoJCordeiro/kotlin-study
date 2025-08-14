package br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums

import kotlinx.serialization.Serializable

@Serializable
enum class AddressType(
    val value: String,
    val description: String,
    val isMain: Boolean
) {
    MAIN("main", "it's a main address", true),
    SECONDARY("secondary", "it's a secondary address", false),
}