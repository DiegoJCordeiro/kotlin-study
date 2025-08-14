package br.com.diegocordeiro.kotlinstudy.domain.enums

enum class AddressType(
    val value: String,
    val description: String,
    val isMain: Boolean
) {
    MAIN("main", "it's a main address", true),
    SECONDARY("secondary", "it's a secondary address", false),
}