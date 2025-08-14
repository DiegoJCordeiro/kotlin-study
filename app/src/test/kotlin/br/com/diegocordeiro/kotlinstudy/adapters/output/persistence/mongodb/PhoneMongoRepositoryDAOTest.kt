package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Phone
import br.com.diegocordeiro.kotlinstudy.domain.model.*
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.PhoneMongoRepositoryDAO
import com.mongodb.client.model.Filters
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertManyResult
import com.mongodb.client.result.UpdateResult
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoCollection
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class PhoneMongoRepositoryDAOTest {

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Phone>
    private lateinit var dao: PhoneMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testPhone: Phone = createPhone()
    private val testPhoneList: List<Phone> = createPhoneList()

    companion object {
        private const val COLLECTION_NAME = "phones"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Phone>(COLLECTION_NAME)).thenReturn(collection)
        dao = PhoneMongoRepositoryDAO(mongoDatabase)
    }



    @Test
    fun `should return all phones`() {
        val iterable: FindIterable<Phone> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testPhoneList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testPhoneList.first(), result.first())
    }

    @Test
    fun `should return phone by ID`() {
        val iterable: FindIterable<Phone> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testPhone)

        val result = dao.findByID("id-test-bson")
        assertEquals(testPhone, result)
    }

    @Test
    fun `should return phones by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Phone> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testPhoneList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one phone`() {
        val result = dao.insertOne(testPhone)
        assertEquals(testPhone, result)
    }

    @Test
    fun `should insert many phones`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testPhoneList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testPhoneList)
        verify(collection, times(1)).insertMany(eq(testPhoneList), any())
    }

    @Test
    fun `should update one phone`() {

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
                update = eq(Document("\$set", testPhone)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testPhone)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testPhone)),
                options = any()
            )
    }

    @Test
    fun `should update many phones`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        val updateBson = testPhoneList.map { Document("\$set", it) }.toList()

        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testPhoneList)
        assertEquals(testPhoneList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateBson = testPhoneList.map { Document("\$set", it) }.toList()

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testPhoneList)
        }
    }

    @Test
    fun `should delete one phone`() {

        val bsonFilter = Filters.and(Filters.eq("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("6839c0fb77889f4e558fd393")
        assertTrue(result)
    }

    @Test
    fun `should delete many phones`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("6839c0fb77889f4e558fd393"))
        assertTrue(result)
    }
}