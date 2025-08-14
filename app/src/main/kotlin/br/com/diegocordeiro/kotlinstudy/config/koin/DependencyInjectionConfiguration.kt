package br.com.diegocordeiro.kotlinstudy.config.koin

import br.com.diegocordeiro.kotlinstudy.application.usecases.address.CreateAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.DeleteAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.GetAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.UpdateAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation.CreateAddressUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation.DeleteAddressUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation.GetAddressUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.implementation.UpdateAddressUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.CreateClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.DeleteClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.GetClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.UpdateClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation.CreateClientUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation.DeleteClientUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation.GetClientUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.implementation.UpdateClientUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.CreateEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.DeleteEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.GetEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.UpdateEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation.CreateEstablishmentUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation.DeleteEstablishmentUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation.GetEstablishmentUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.implementation.UpdateEstablishmentUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.CreatePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.DeletePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.GetPhoneUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.UpdatePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation.CreatePhoneUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation.DeletePhoneUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation.GetPhoneUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.implementation.UpdatePhoneUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.CreatePlanUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.DeletePlanUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.GetPlanUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.UpdatePlanUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation.CreatePlanUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation.DeletePlanUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation.GetPlanUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.implementation.UpdatePlanUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.CreateProductUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.DeleteProductUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.GetProductUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.UpdateProductUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation.CreateProductUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation.DeleteProductUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation.GetProductUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.implementation.UpdateProductUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.CreateProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.DeleteProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.GetProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.UpdateProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation.CreateProfessionalUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation.DeleteProfessionalUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation.GetProfessionalUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.implementation.UpdateProfessionalUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.CreateScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.DeleteScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.GetScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.UpdateScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation.CreateScheduleUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation.DeleteScheduleUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation.GetScheduleUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.implementation.UpdateScheduleUseCaseImpl
import br.com.diegocordeiro.kotlinstudy.config.mongo.MongoDBConfiguration
import br.com.diegocordeiro.kotlinstudy.config.redis.RedisDBConfiguration
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.*
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.slf4j.LoggerFactory


private val config: ApplicationConfig = HoconApplicationConfig(ConfigFactory.load())

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(databaseModule, repositoryModule, useCaseModule)
    }
}

// DatabaseModule.kt
private val databaseModule = module {
    val logger = LoggerFactory.getLogger("Database")
    logger.info("START - DI - Database")
    single {
        RedisDBConfiguration(
            config.property("ktor.app.redis.uri").getString()
        ).getDatabase()
    }
    single {
        MongoDBConfiguration(
            config.property("ktor.app.mongo.uri").getString(),
            config.property("ktor.app.mongo.database").getString()
        ).getDatabase()
    }
    logger.info("END - DI - Database")
}

// RepositoryModule.kt
private val repositoryModule = module {

    val logger = LoggerFactory.getLogger("DAO")
    logger.info("START - DI - DAOs")

    single<MongoAddressCRUDRepository> { AddressMongoRepositoryDAO(get()) }
    single<MongoClientCRUDRepository> { ClientMongoRepositoryDAO(get()) }
    single<MongoCouponCRUDRepository> { CouponMongoRepositoryDAO(get()) }
    single<MongoEstablishmentCRUDRepository> { EstablishmentMongoRepositoryDAO(get()) }
    single<MongoPhoneCRUDRepository> { PhoneMongoRepositoryDAO(get()) }
    single<MongoPlanCRUDRepository> { PlanMongoRepositoryDAO(get()) }
    single<MongoProductCRUDRepository> { ProductMongoRepositoryDAO(get()) }
    single<MongoProfessionalCRUDRepository> { ProfessionalMongoRepositoryDAO(get()) }
    single<MongoScheduleCRUDRepository> { ScheduleMongoRepositoryDAO(get()) }
    single<MongoSettingsCRUDRepository> { SettingsMongoRepositoryDAO(get()) }
    single<MongoSubscriptionCRUDRepository> { SubscriptionMongoRepositoryDAO(get()) }

    logger.info("END - DI - DAOs")
}

// UseCaseModule.kt
private val useCaseModule = module {

    val logger = LoggerFactory.getLogger("Use Case")
    logger.info("START - DI - Use Cases")

    // Address
    single<CreateAddressUseCase>  { CreateAddressUseCaseImpl(get<MongoAddressCRUDRepository>()) }
    single<DeleteAddressUseCase>  { DeleteAddressUseCaseImpl(get<MongoAddressCRUDRepository>()) }
    single<UpdateAddressUseCase>  { UpdateAddressUseCaseImpl(get<MongoAddressCRUDRepository>()) }
    single<GetAddressUseCase>  { GetAddressUseCaseImpl(get<MongoAddressCRUDRepository>()) }

    // Client
    single<CreateClientUseCase>  { CreateClientUseCaseImpl(get<MongoClientCRUDRepository>()) }
    single<DeleteClientUseCase>  { DeleteClientUseCaseImpl(get<MongoClientCRUDRepository>()) }
    single<UpdateClientUseCase>  { UpdateClientUseCaseImpl(get<MongoClientCRUDRepository>()) }
    single<GetClientUseCase> { GetClientUseCaseImpl(get<MongoClientCRUDRepository>()) }

    // Establishment
    single<GetEstablishmentUseCase>  { GetEstablishmentUseCaseImpl(get<MongoEstablishmentCRUDRepository>()) }
    single<CreateEstablishmentUseCase>  { CreateEstablishmentUseCaseImpl(get<MongoEstablishmentCRUDRepository>()) }
    single<DeleteEstablishmentUseCase>  { DeleteEstablishmentUseCaseImpl(get<MongoEstablishmentCRUDRepository>()) }
    single<UpdateEstablishmentUseCase>  { UpdateEstablishmentUseCaseImpl(get<MongoEstablishmentCRUDRepository>()) }

    // Phone
    single<GetPhoneUseCase>  { GetPhoneUseCaseImpl(get<MongoPhoneCRUDRepository>()) }
    single<CreatePhoneUseCase>  { CreatePhoneUseCaseImpl(get<MongoPhoneCRUDRepository>()) }
    single<DeletePhoneUseCase>  { DeletePhoneUseCaseImpl(get<MongoPhoneCRUDRepository>()) }
    single<UpdatePhoneUseCase>  { UpdatePhoneUseCaseImpl(get<MongoPhoneCRUDRepository>()) }

    // Plan
    single<GetPlanUseCase>  { GetPlanUseCaseImpl(get<MongoPlanCRUDRepository>()) }
    single<CreatePlanUseCase>  { CreatePlanUseCaseImpl(get<MongoPlanCRUDRepository>()) }
    single<DeletePlanUseCase>  { DeletePlanUseCaseImpl(get<MongoPlanCRUDRepository>()) }
    single<UpdatePlanUseCase>  { UpdatePlanUseCaseImpl(get<MongoPlanCRUDRepository>()) }

    // Product
    single<GetProductUseCase>  { GetProductUseCaseImpl(get<MongoProductCRUDRepository>()) }
    single<CreateProductUseCase>  { CreateProductUseCaseImpl(get<MongoProductCRUDRepository>()) }
    single<DeleteProductUseCase>  { DeleteProductUseCaseImpl(get<MongoProductCRUDRepository>()) }
    single<UpdateProductUseCase>  { UpdateProductUseCaseImpl(get<MongoProductCRUDRepository>()) }

    // Professional
    single<GetProfessionalUseCase>  { GetProfessionalUseCaseImpl(get<MongoProfessionalCRUDRepository>()) }
    single<CreateProfessionalUseCase>  { CreateProfessionalUseCaseImpl(get<MongoProfessionalCRUDRepository>()) }
    single<DeleteProfessionalUseCase>  { DeleteProfessionalUseCaseImpl(get<MongoProfessionalCRUDRepository>()) }
    single<UpdateProfessionalUseCase>  { UpdateProfessionalUseCaseImpl(get<MongoProfessionalCRUDRepository>()) }

    // Schedule
    single<GetScheduleUseCase>  { GetScheduleUseCaseImpl(get<MongoScheduleCRUDRepository>()) }
    single<CreateScheduleUseCase>  { CreateScheduleUseCaseImpl(get<MongoScheduleCRUDRepository>()) }
    single<DeleteScheduleUseCase>  { DeleteScheduleUseCaseImpl(get<MongoScheduleCRUDRepository>()) }
    single<UpdateScheduleUseCase>  { UpdateScheduleUseCaseImpl(get<MongoScheduleCRUDRepository>()) }

    logger.info("END - DI - Use Cases")
}