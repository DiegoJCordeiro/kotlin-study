package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProfessionalCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Professional
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.ObjectId

class ProfessionalMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoProfessionalCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "professionals"
    }

    override fun findAll(): List<Professional> {

        val collection = mongoDatabase.getCollection<Professional>(COLLECTION_NAME)
        val professionalsFound: FindIterable<Professional> = collection.find()

        return professionalsFound.toList()
    }

    override fun findByID(id: String): Professional? {

        val collection = mongoDatabase.getCollection<Professional>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val professionalFound: FindIterable<Professional> = collection.find(filters)

        return professionalFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Professional> {

        val collection = mongoDatabase.getCollection<Professional>(COLLECTION_NAME)
        val professionalFound: FindIterable<Professional> = collection.find(createBSONFilter(filtersMap))

        return professionalFound.toList()
    }

    override fun insertOne(document: Professional): Professional {

        mongoDatabase.getCollection<Professional>(COLLECTION_NAME).insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Professional>): Boolean = mongoDatabase.getCollection<Professional>(
        COLLECTION_NAME
    )
        .insertMany(documents)
        .wasAcknowledged()

    override fun updateOne(filtersMap: Map<String, String>, document: Professional): Professional {

        val result = mongoDatabase.getCollection<Professional>(COLLECTION_NAME)
            .updateOne(
                createBSONFilter(filtersMap),
                Document("\$set", document)
            )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Professional>): List<Professional> {

        val result = mongoDatabase.getCollection<Professional>(COLLECTION_NAME)
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

        val collection = mongoDatabase.getCollection<Professional>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Professional>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf("_id" to ids))
        )

        return result.deletedCount != 0L
    }

}