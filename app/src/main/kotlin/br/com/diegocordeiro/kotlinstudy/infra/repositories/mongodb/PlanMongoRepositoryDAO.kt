package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPlanCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Plan
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.ObjectId

class PlanMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoPlanCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "plans"
    }

    override fun findAll(): List<Plan> {

        val collection = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
        val productsFound: FindIterable<Plan> = collection.find()

        return productsFound.toList()
    }

    override fun findByID(id: String): Plan? {

        val collection = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val productFound: FindIterable<Plan> = collection.find(filters)

        return productFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Plan> {

        val collection = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
        val productsFound: FindIterable<Plan> = collection.find(createBSONFilter(filtersMap))

        return productsFound.toList()
    }

    override fun insertOne(document: Plan): Plan {

        mongoDatabase.getCollection<Plan>(COLLECTION_NAME).insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Plan>): Boolean = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
        .insertMany(documents)
        .wasAcknowledged()

    override fun updateOne(filtersMap: Map<String, String>, document: Plan): Plan {

        val result = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
            .updateOne(
                createBSONFilter(filtersMap),
                Document("\$set", document)
            )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Plan>): List<Plan> {

        val result = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
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

        val collection = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Plan>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf(
                "_id" to ids
            ))
        )

        return result.deletedCount != 0L
    }
}