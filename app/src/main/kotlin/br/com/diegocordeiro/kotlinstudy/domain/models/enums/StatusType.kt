package br.com.diegocordeiro.kotlinstudy.domain.models.enums

enum class StatusType(
    val value: Boolean,
    val description: String
) {
    ENABLE(true, "It's Enable"),
    DISABLE(false, "It's Disable")
}