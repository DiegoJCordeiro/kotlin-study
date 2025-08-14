package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoSettingsCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Settings
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.ObjectId

class SettingsMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoSettingsCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "settings"
    }

    override fun findAll(): List<Settings> {

        val collection = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
        val settingsFound: FindIterable<Settings> = collection.find()

        return settingsFound.toList()
    }

    override fun findByID(id: String): Settings? {

        val collection = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val settingsFound: FindIterable<Settings> = collection.find(filters)

        return settingsFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Settings> {

        val collection = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
        val settingsFound: FindIterable<Settings> = collection.find(createBSONFilter(filtersMap))

        return settingsFound.toList()
    }

    override fun insertOne(document: Settings): Settings {

        mongoDatabase.getCollection<Settings>(COLLECTION_NAME).insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Settings>): Boolean = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
        .insertMany(documents)
        .wasAcknowledged()

    override fun updateOne(filtersMap: Map<String, String>, document: Settings): Settings {

        val result = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
            .updateOne(
                createBSONFilter(filtersMap),
                Document("\$set", document)
            )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Settings>): List<Settings> {

        val result = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
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

        val collection = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to ObjectId(id))))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Settings>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf(
                "_id" to ids.map { ObjectId(it) }
            ))
        )

        return result.deletedCount != 0L
    }
}