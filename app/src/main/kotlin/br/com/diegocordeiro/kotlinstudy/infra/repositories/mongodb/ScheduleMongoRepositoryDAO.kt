package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoScheduleCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Schedule
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document

class ScheduleMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoScheduleCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "schedules"
    }

    override fun findAll(): List<Schedule> = mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)
        .find()
        .toList()

    override fun findByID(id: String): Schedule? = mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)
        .find(Filters.and(Filters.eq("_id", id)))
        .firstOrNull()

    override fun findBy(filtersMap: Map<String, String>): List<Schedule> = mongoDatabase.getCollection<Schedule>(
        COLLECTION_NAME
    )
        .find(createBSONFilter(filtersMap))
        .toList()

    override fun insertOne(document: Schedule): Schedule {

        mongoDatabase.getCollection<Schedule>(COLLECTION_NAME).insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Schedule>): Boolean = mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)
        .insertMany(documents)
        .wasAcknowledged()

    override fun updateOne(filtersMap: Map<String, String>, document: Schedule): Schedule {

        val result = mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)
            .updateOne(
                createBSONFilter(filtersMap),
                Document("\$set", document)
            )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Schedule>): List<Schedule> {

        val result = mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)
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

        val collection = mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)
        val result = collection.deleteMany(createBSONFilter(filtersWithList = mapOf("_id" to ids)))

        return result.deletedCount != 0L
    }
}