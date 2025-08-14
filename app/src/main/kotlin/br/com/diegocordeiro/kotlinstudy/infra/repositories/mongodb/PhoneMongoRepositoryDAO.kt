package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPhoneCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Phone
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document

class PhoneMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoPhoneCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "phones"
    }

    override fun findAll(): List<Phone> {

        val collection = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
        val phonesFound: FindIterable<Phone> = collection.find()

        return phonesFound.toList()
    }

    override fun findByID(id: String): Phone? {

        val collection = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val phoneFound: FindIterable<Phone> = collection.find(filters)

        return phoneFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Phone> {

        val collection = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
        val phonesFound: FindIterable<Phone> = collection.find(createBSONFilter(filtersMap))

        return phonesFound.toList()
    }

    override fun insertOne(document: Phone): Phone {

        val collection = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
        collection.insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Phone>): Boolean = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
                .insertMany(documents)
                .wasAcknowledged()

    override fun updateOne(filtersMap: Map<String, String>, document: Phone): Phone {

        val result = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
                .updateOne(
                    createBSONFilter(filtersMap),
                    Document("\$set", document)
                )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Phone>): List<Phone> {

        val result = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
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

        val collection = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Phone>(COLLECTION_NAME)
        val result = collection.deleteMany(createBSONFilter(filtersWithList = mapOf("_id" to ids.map { it })))

        return result.deletedCount != 0L
    }
}