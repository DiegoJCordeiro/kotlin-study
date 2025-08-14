package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoEstablishmentCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Establishment
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.ObjectId

class EstablishmentMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoEstablishmentCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "establishments"
    }

    override fun findAll(): List<Establishment> {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)
        val establishmentFound: FindIterable<Establishment> = collection.find()

        return establishmentFound.toList()
    }

    override fun findByID(id: String): Establishment? {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val establishmentFound: FindIterable<Establishment> = collection.find(filters)

        return establishmentFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Establishment> {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)
        val establishmentFound: FindIterable<Establishment> = collection.find(createBSONFilter(filtersMap))

        return establishmentFound.toList()
    }

    override fun insertOne(document: Establishment): Establishment {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)
        collection.insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Establishment>): Boolean {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)
        val documentsInserted = collection.insertMany(documents)

        return documentsInserted.wasAcknowledged()
    }

    override fun updateOne(filtersMap: Map<String, String>, document: Establishment): Establishment {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)

        val updateBson = Document("\$set", document)
        val result = collection.updateOne(createBSONFilter(filtersMap), updateBson)

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Establishment>): List<Establishment> {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)

        val updateBson = documents.map { Document("\$set", it) }.toList()
        val result = collection.updateMany(createBSONFilter(filtersMap), updateBson)

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The documents wasn't update")
        }

        return documents
    }

    override fun deleteOne(id: String): Boolean {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf("_id" to ids.map { ObjectId(it) }))
        )

        return result.deletedCount != 0L
    }
}