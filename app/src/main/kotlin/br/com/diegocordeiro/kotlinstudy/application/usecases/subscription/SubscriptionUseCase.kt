package br.com.diegocordeiro.kotlinstudy.application.usecases.subscription

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.SubscriptionCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.SubscriptionUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.SubscriptionDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionDeleteOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoSubscriptionCRUDRepository

interface CreateSubscriptionUseCase {
    val subscriptionMongoRepositoryDAO: MongoSubscriptionCRUDRepository
    fun execute(subscriptionCreateInput: SubscriptionCreateInput): SubscriptionCreateOutput
}

interface UpdateSubscriptionUseCase {
    val subscriptionMongoRepositoryDAO: MongoSubscriptionCRUDRepository
    fun execute(subscriptionUpdateInput: SubscriptionUpdateInput): SubscriptionUpdateOutput
}

interface DeleteSubscriptionUseCase {
    val subscriptionMongoRepositoryDAO: MongoSubscriptionCRUDRepository
    fun execute(subscriptionDeleteInput: SubscriptionDeleteInput): SubscriptionDeleteOutput
}