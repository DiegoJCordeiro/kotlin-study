package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoAddressCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Address

import com.mongodb.client.model.Filters
import com.mongodb.client.model.InsertManyOptions
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.result.InsertManyResult
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase

import org.bson.Document
import org.bson.types.ObjectId

class AddressMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoAddressCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "addresses"
    }

    override fun findAll(): List<Address> {

        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        val addressesFound: FindIterable<Address> = collection.find()

        return addressesFound.toList()
    }

    override fun findByID(id: String): Address? {

        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val addressFound: FindIterable<Address> = collection.find(filters)

        return addressFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Address> {

        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        val addressFound: FindIterable<Address> = collection.find(createBSONFilter(filtersMap))

        return addressFound.toList()
    }

    override fun insertOne(document: Address): Address {

        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        collection.insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Address>): Boolean {

        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        val insertedManyResult: InsertManyResult = collection.insertMany(documents= documents, options = InsertManyOptions())

        return insertedManyResult.wasAcknowledged()
    }

    override fun updateOne(filtersMap: Map<String, String>, document: Address): Address {

        val result = update(filters = filtersMap, document = document)

        if (result == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Address>): List<Address> {

        val result = update(filters = filtersMap, documents = documents)

        if (result == 0L) {
            throw NoSuchElementException("The documents wasn't update")
        }

        return documents
    }

    override fun deleteOne(id: String): Boolean {

        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to id)))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        val result = collection.deleteMany(filter = createBSONFilter(
            filtersWithList = mapOf("_id" to ids.map { ObjectId(it) }
            )
        ))

        return result.deletedCount != 0L
    }

    private fun update(filters: Map<String, String>, document: Address? = null, documents: List<Address> = listOf()): Long {
        val collection = mongoDatabase.getCollection<Address>(COLLECTION_NAME)
        val bsonFilter = createBSONFilter(filters)

        val result = if(document != null){
            collection.updateOne(filter = bsonFilter, update = Document("\$set", document), options = UpdateOptions())
        } else if (documents.isNotEmpty()) {
            collection.updateMany(filter = bsonFilter, update = Document("\$set", documents), options = UpdateOptions())
        } else {
            throw NoSuchElementException("Not has documents to update")
        }

        return result.matchedCount
    }

}