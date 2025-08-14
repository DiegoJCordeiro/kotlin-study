package br.com.diegocordeiro.kotlinstudy.application.usecases.subscription.implementation

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.SubscriptionUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.SubscriptionUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.usecases.subscription.UpdateSubscriptionUseCase
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoSubscriptionCRUDRepository

class UpdateSubscriptionUseCaseImpl(
    override val subscriptionMongoRepositoryDAO: MongoSubscriptionCRUDRepository
): UpdateSubscriptionUseCase {

    override fun execute(subscriptionUpdateInput: SubscriptionUpdateInput): SubscriptionUpdateOutput {
        TODO("Not yet implemented")
    }
}