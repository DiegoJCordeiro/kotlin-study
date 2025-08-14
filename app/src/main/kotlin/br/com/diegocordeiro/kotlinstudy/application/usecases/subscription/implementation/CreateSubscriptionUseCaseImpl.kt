package br.com.diegocordeiro.kotlinstudy.application.usecases.subscription.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.SubscriptionCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.subscription.CreateSubscriptionUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoSubscriptionCRUDRepository

class CreateSubscriptionUseCaseImpl(
    override val subscriptionMongoRepositoryDAO: MongoSubscriptionCRUDRepository
): CreateSubscriptionUseCase {

    override fun execute(subscriptionCreateInput: SubscriptionCreateInput): SubscriptionCreateOutput {
        TODO("Not yet implemented")
    }
}