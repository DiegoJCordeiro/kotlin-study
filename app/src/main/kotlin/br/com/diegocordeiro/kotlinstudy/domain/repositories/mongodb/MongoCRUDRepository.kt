package br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.*
import com.mongodb.client.model.Filters
import org.bson.conversions.Bson

sealed interface MongoCRUDRepository<T, ID> {
    fun findAll(): List<T>
    fun findByID(id: ID): T?
    fun findBy(filtersMap: Map<String, String>): List<T>
    fun insertOne(document: T): T
    fun insertMany(documents: List<T>): Boolean
    fun updateOne(filtersMap: Map<String, String>, document: T): T
    fun updateMany(filtersMap: Map<String, String>, documents: List<T>): List<T>
    fun deleteOne(id: String): Boolean
    fun deleteMany(ids: List<String>): Boolean
}

interface MongoAddressCRUDRepository: MongoCRUDRepository<Address, String>

interface MongoClientCRUDRepository: MongoCRUDRepository<Client, String>

interface MongoEstablishmentCRUDRepository: MongoCRUDRepository<Establishment, String>

interface MongoPhoneCRUDRepository: MongoCRUDRepository<Phone, String>

interface MongoProductCRUDRepository: MongoCRUDRepository<Product, String>

interface MongoProfessionalCRUDRepository: MongoCRUDRepository<Professional, String>

interface MongoScheduleCRUDRepository: MongoCRUDRepository<Schedule, String>

interface MongoCouponCRUDRepository: MongoCRUDRepository<Coupon, String>

interface MongoPlanCRUDRepository: MongoCRUDRepository<Plan, String>

interface MongoSubscriptionCRUDRepository: MongoCRUDRepository<Subscription, String>

interface MongoSettingsCRUDRepository: MongoCRUDRepository<Settings, String>

fun createBSONFilter(
    filtersDefault: Map<String, Any>? = null,
    filtersWithList: Map<String, List<Any>>? = null): Bson
{

    val defaultFilters = filtersDefault.orEmpty().map { (field, value) ->
       Filters.eq(field, value)
    }

    val listFilters = filtersWithList.orEmpty().map { (field, values) ->
        Filters.`in`(field, values)
    }

    val combinedFilters = defaultFilters + listFilters

    return if (combinedFilters.isNotEmpty()) {
        Filters.and(combinedFilters)
    } else {
        Filters.empty()
    }
}