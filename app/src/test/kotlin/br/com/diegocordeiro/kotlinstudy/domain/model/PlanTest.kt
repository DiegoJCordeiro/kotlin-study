package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Plan
import br.com.diegocordeiro.kotlinstudy.domain.models.enums.StatusType
import java.math.BigDecimal
import java.time.LocalDateTime

fun createPlan(): Plan = Plan(
    id = "id-test-bson",
    title = "title-bson",
    description = "description-bson",
    statusType = StatusType.ENABLE,
    price = BigDecimal.valueOf(100),
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)

fun createPlanList(): List<Plan> = listOf(
    Plan(
        id = "id-test-bson",
        title = "title-bson",
        description = "description-bson",
        statusType = StatusType.ENABLE,
        price = BigDecimal.valueOf(100),
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
)