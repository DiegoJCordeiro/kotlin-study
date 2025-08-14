package br.com.diegocordeiro.kotlinstudy.application.usecases.subscription.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.SubscriptionDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.subscription.DeleteSubscriptionUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoSubscriptionCRUDRepository

class DeleteSubscriptionUseCaseImpl(
    override val subscriptionMongoRepositoryDAO: MongoSubscriptionCRUDRepository
): DeleteSubscriptionUseCase {

    override fun execute(subscriptionDeleteInput: SubscriptionDeleteInput): SubscriptionDeleteOutput {
        TODO("Not yet implemented")
    }
}