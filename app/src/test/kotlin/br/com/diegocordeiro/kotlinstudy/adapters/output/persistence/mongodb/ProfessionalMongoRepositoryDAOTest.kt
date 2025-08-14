package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Professional
import br.com.diegocordeiro.kotlinstudy.domain.model.*
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.ProfessionalMongoRepositoryDAO
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

class ProfessionalMongoRepositoryDAOTest {

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Professional>
    private lateinit var dao: ProfessionalMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testProduct: Professional = createProfessional()
    private val testProductList: List<Professional> = createProfessionalList()

    companion object {
        private const val COLLECTION_NAME = "professionals"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Professional>(COLLECTION_NAME)).thenReturn(collection)
        dao = ProfessionalMongoRepositoryDAO(mongoDatabase)
    }

    @Test
    fun `should return all professionals`() {
        val iterable: FindIterable<Professional> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testProductList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testProductList.first(), result.first())
    }

    @Test
    fun `should return professional by ID`() {
        val iterable: FindIterable<Professional> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testProduct)

        val result = dao.findByID("id-test-bson")
        assertEquals(testProduct, result)
    }

    @Test
    fun `should return professionals by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Professional> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testProductList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one professional`() {
        val result = dao.insertOne(testProduct)
        assertEquals(testProduct, result)
    }

    @Test
    fun `should insert many professionals`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testProductList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testProductList)
        verify(collection, times(1)).insertMany(eq(testProductList), any())
    }

    @Test
    fun `should update one professional`() {

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
                update = eq(Document("\$set", testProduct)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testProduct)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testProduct)),
                options = any()
            )
    }

    @Test
    fun `should update many professionals`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        val updateBson = testProductList.map { Document("\$set", it) }.toList()

        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testProductList)
        assertEquals(testProductList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateBson = testProductList.map { Document("\$set", it) }.toList()

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testProductList)
        }
    }

    @Test
    fun `should delete one professional`() {

        val bsonFilter = Filters.and(Filters.eq("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("6839c0fb77889f4e558fd393")
        assertTrue(result)
    }

    @Test
    fun `should delete many professionals`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("6839c0fb77889f4e558fd393"))
        assertTrue(result)
    }
}