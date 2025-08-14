package br.com.diegocordeiro.kotlinstudy.application.dto.inbound

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.enums.Status
import java.time.LocalDateTime


data class CouponCreateInput(
    val id: String?,
    val description: String,
    val establishments: List<EstablishmentCreateInput>,
    val percentage: Int,
    val enabled: Status,
    val expiration: LocalDateTime,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null,
)

data class CouponUpdateInput(
    val id: String,
    val description: String,
    val establishments: List<EstablishmentUpdateInput>,
    val percentage: Int,
    val enabled: Status,
    val expiration: LocalDateTime,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null,
)

data class CouponDeleteInput(
    val id: String
)