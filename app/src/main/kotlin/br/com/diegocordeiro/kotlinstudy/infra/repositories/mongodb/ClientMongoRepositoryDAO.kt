package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoClientCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Client
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document

class ClientMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoClientCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "clients"
    }

    override fun findAll(): List<Client> {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val clientsFound: FindIterable<Client> = collection.find()

        return clientsFound.toList()
    }

    override fun findByID(id: String): Client? {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val clientsFound: FindIterable<Client> = collection.find(filters)

        return clientsFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Client> {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val clientsFound: FindIterable<Client> = collection.find(createBSONFilter(filtersMap))

        return clientsFound.toList()
    }

    override fun insertOne(document: Client): Client {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        collection.insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Client>): Boolean {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val documentsInserted = collection.insertMany(documents)

        return documentsInserted.wasAcknowledged()
    }

    override fun updateOne(filtersMap: Map<String, String>, document: Client): Client {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val bsonFilter = createBSONFilter(filtersMap)

        val updateBson = Document("\$set", document)

        val result = collection.updateOne(filter = bsonFilter, update = updateBson, options = UpdateOptions())

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Client>): List<Client> {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val bsonFilter = createBSONFilter(filtersMap)

        val updateBson = documents.map { Document("\$set", it) }.toList()

        val result = collection.updateMany(bsonFilter, updateBson)

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The documents wasn't update")
        }

        return documents
    }

    override fun deleteOne(id: String): Boolean {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Client>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf(
                "_id" to ids
            ))
        )

        return result.deletedCount != 0L
    }
}