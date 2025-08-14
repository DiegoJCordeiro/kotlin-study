package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoSubscriptionCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Subscription
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.ObjectId

class SubscriptionMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoSubscriptionCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "subscriptions"
    }

    override fun findAll(): List<Subscription> {

        val collection = mongoDatabase.getCollection<Subscription>(COLLECTION_NAME)
        val subscriptionFound: FindIterable<Subscription> = collection.find()

        return subscriptionFound.toList()
    }

    override fun findByID(id: String): Subscription? {

        val collection = mongoDatabase.getCollection<Subscription>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val subscriptionFound: FindIterable<Subscription> = collection.find(filters)

        return subscriptionFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Subscription> {

        val collection = mongoDatabase.getCollection<Subscription>(COLLECTION_NAME)
        val subscriptionsFound: FindIterable<Subscription> = collection.find(createBSONFilter(filtersMap))

        return subscriptionsFound.toList()
    }

    override fun insertOne(document: Subscription): Subscription {

        mongoDatabase.getCollection<Subscription>(COLLECTION_NAME).insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Subscription>): Boolean = mongoDatabase.getCollection<Subscription>(
        COLLECTION_NAME
    )
        .insertMany(documents)
        .wasAcknowledged()

    override fun updateOne(filtersMap: Map<String, String>, document: Subscription): Subscription {

        val result = mongoDatabase.getCollection<Subscription>(COLLECTION_NAME)
            .updateOne(
                createBSONFilter(filtersMap),
                Document("\$set", document)
            )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Subscription>): List<Subscription> {

        val result = mongoDatabase.getCollection<Subscription>(COLLECTION_NAME)
            .updateMany(
                createBSONFilter(filtersMap),
                documents.map { Document("\$set", it) }.toList()
            )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The documents wasn't update")
        }

        return documents
    }

    override fun deleteOne(id: String): Boolean {

        val collection = mongoDatabase.getCollection<Subscription>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to ObjectId(id))))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Subscription>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf(
                "_id" to ids.map { ObjectId(it) }
            ))
        )

        return result.deletedCount != 0L
    }
}