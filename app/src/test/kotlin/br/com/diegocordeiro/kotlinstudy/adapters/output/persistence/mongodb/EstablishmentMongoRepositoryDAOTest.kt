package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Establishment
import br.com.diegocordeiro.kotlinstudy.domain.model.*
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.EstablishmentMongoRepositoryDAO
import com.mongodb.client.model.Filters
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertManyResult
import com.mongodb.client.result.UpdateResult
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoCollection
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class EstablishmentMongoRepositoryDAOTest(){

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Establishment>
    private lateinit var dao: EstablishmentMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testEstablishment: Establishment = createEstablishment()
    private val testEstablishmentList: List<Establishment> = createEstablishmentList()

    companion object {
        private const val COLLECTION_NAME = "establishments"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Establishment>(COLLECTION_NAME)).thenReturn(collection)
        dao = EstablishmentMongoRepositoryDAO(mongoDatabase)
    }

    @Test
    fun `should return all establishments`() {
        val iterable: FindIterable<Establishment> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testEstablishmentList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testEstablishmentList.first(), result.first())
    }

    @Test
    fun `should return establishment by ID`() {
        val iterable: FindIterable<Establishment> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testEstablishment)

        val result = dao.findByID("id-test-bson")
        assertEquals(testEstablishment, result)
    }

    @Test
    fun `should return establishments by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Establishment> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testEstablishmentList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one establishment`() {
        val result = dao.insertOne(testEstablishment)
        assertEquals(testEstablishment, result)
    }

    @Test
    fun `should insert many establishments`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testEstablishmentList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testEstablishmentList)
        verify(collection, times(1)).insertMany(eq(testEstablishmentList), any())
    }

    @Test
    fun `should update one establishment`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult = mock<UpdateResult> {
            on { wasAcknowledged() } doReturn true
            on { matchedCount } doReturn 1L
        }
        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(
            collection.updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testEstablishment)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testEstablishment)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testEstablishment)),
                options = any()
            )
    }

    @Test
    fun `should update many establishment`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        val updateBson = testEstablishmentList.map { Document("\$set", it) }.toList()

        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testEstablishmentList)
        assertEquals(testEstablishmentList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateBson = testEstablishmentList.map { Document("\$set", it) }.toList()

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testEstablishmentList)
        }
    }

    @Test
    fun `should delete one establishment`() {

        val bsonFilter = Filters.and(Filters.eq("_id","60b8d2b5f0e4e4001c8b4567"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("60b8d2b5f0e4e4001c8b4567")
        assertTrue(result)
    }

    @Test
    fun `should delete many establishment`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", ObjectId("6839c0fb77889f4e558fd393")))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("6839c0fb77889f4e558fd393"))
        assertTrue(result)
    }
}