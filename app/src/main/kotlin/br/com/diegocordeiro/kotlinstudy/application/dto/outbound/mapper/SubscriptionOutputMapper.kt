package br.com.diegocordeiro.kotlinstudy.application.dto.outbound.mapper

import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionUpdateOutput
import br.com.diegocordeiro.kotlinstudy.domain.models.Subscription

fun Subscription.toCreateOutput(): SubscriptionCreateOutput = SubscriptionCreateOutput(
    id = this.id
)

fun Subscription.toUpdateOutput(): SubscriptionUpdateOutput = SubscriptionUpdateOutput(
    id = this.id.orEmpty()
)