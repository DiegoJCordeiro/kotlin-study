package br.com.diegocordeiro.kotlinstudy.application.dto.outbound

data class SubscriptionCreateOutput(
    val id: String?
)

data class SubscriptionUpdateOutput(
    val id: String
)

data class SubscriptionDeleteOutput(
    val id: String
)