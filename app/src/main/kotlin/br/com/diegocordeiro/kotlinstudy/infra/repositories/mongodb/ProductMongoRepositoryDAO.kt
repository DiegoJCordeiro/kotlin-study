package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProductCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Product
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document

class ProductMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoProductCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "products"
    }

    override fun findAll(): List<Product> {

        val collection = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
        val productsFound: FindIterable<Product> = collection.find()

        return productsFound.toList()
    }

    override fun findByID(id: String): Product? {

        val collection = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val productFound: FindIterable<Product> = collection.find(filters)

        return productFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Product> {

        val collection = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
        val productsFound: FindIterable<Product> = collection.find(createBSONFilter(filtersMap))

        return productsFound.toList()
    }

    override fun insertOne(document: Product): Product {

        mongoDatabase.getCollection<Product>(COLLECTION_NAME).insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Product>): Boolean = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
        .insertMany(documents)
        .wasAcknowledged()

    override fun updateOne(filtersMap: Map<String, String>, document: Product): Product {

        val result = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
            .updateOne(
                createBSONFilter(filtersMap),
                Document("\$set", document)
            )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Product>): List<Product> {

        val result = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
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

        val collection = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Product>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf(
                "_id" to ids
            ))
        )

        return result.deletedCount != 0L
    }
}