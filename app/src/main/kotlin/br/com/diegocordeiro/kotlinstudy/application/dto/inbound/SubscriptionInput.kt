package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.Status
import java.time.LocalDateTime


data class SubscriptionCreateInput(
    val id: String?,
    val establishmentId: String,
    val planId: String,
    val statusType: Status,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

data class SubscriptionUpdateInput(
    val id: String,
    val establishmentId: String,
    val planId: String,
    val statusType: Status,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

data class SubscriptionDeleteInput(
    val id: String
)