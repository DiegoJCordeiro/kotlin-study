package br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoCouponCRUDRepository
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.createBSONFilter
import br.com.diegocordeiro.kotlinstudy.domain.models.Coupon
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.ObjectId

class CouponMongoRepositoryDAO(
    private val mongoDatabase: MongoDatabase
): MongoCouponCRUDRepository {

    companion object {
        private const val COLLECTION_NAME = "coupons"
    }

    override fun findAll(): List<Coupon> {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)
        val couponsFound: FindIterable<Coupon> = collection.find()

        return couponsFound.toList()
    }

    override fun findByID(id: String): Coupon? {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)
        val filters = Filters.and(Filters.eq("_id", id))
        val couponsFound: FindIterable<Coupon> = collection.find(filters)

        return couponsFound.firstOrNull()
    }

    override fun findBy(filtersMap: Map<String, String>): List<Coupon> {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)
        val couponsFound: FindIterable<Coupon> = collection.find(createBSONFilter(filtersMap))

        return couponsFound.toList()
    }

    override fun insertOne(document: Coupon): Coupon {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)
        collection.insertOne(document)

        return document
    }

    override fun insertMany(documents: List<Coupon>): Boolean {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)
        val documentsInserted = collection.insertMany(documents)

        return documentsInserted.wasAcknowledged()
    }

    override fun updateOne(filtersMap: Map<String, String>, document: Coupon): Coupon {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)

        val updateBson = Document("\$set", document)
        val result = collection.updateOne(createBSONFilter(filtersMap), updateBson)

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The document wasn't update")
        }

        return document
    }

    override fun updateMany(filtersMap: Map<String, String>, documents: List<Coupon>): List<Coupon> {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)

        val result = collection.updateMany(
            createBSONFilter(filtersMap),
            documents.map { Document("\$set", it) }.toList()
        )

        if (result.matchedCount == 0L) {
            throw NoSuchElementException("The documents wasn't update")
        }

        return documents
    }

    override fun deleteOne(id: String): Boolean {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)
        val result = collection.deleteOne(createBSONFilter(filtersDefault = mapOf("_id" to ObjectId(id))))

        return result.deletedCount != 0L
    }

    override fun deleteMany(ids: List<String>): Boolean {

        val collection = mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)
        val result = collection.deleteMany(
            createBSONFilter(filtersWithList = mapOf(
                "_id" to ids.map { ObjectId(it) }
            ))
        )

        return result.deletedCount != 0L
    }

}