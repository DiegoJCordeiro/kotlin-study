package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Coupon
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import java.time.LocalDateTime

fun createCoupon(): Coupon = Coupon(
    id = "id-test-bson",
    description = "description-test",
    establishments = createEstablishmentList(),
    percentage = 10,
    enabled = StatusType.ENABLE,
    expiration = LocalDateTime.now()
)

fun createCouponList(): List<Coupon> = listOf(
    createCoupon()
)