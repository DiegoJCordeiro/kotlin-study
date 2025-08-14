package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Client
import br.com.diegocordeiro.kotlinstudy.domain.model.*
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.ClientMongoRepositoryDAO
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

class ClientMongoRepositoryDAOTest {

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Client>
    private lateinit var dao: ClientMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testClient: Client = createClient()
    private val testClientList: List<Client> = createClientList()

    companion object {
        private const val COLLECTION_NAME = "clients"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Client>(COLLECTION_NAME)).thenReturn(collection)
        dao = ClientMongoRepositoryDAO(mongoDatabase)
    }

    @Test
    fun `should return all clients`() {
        val iterable: FindIterable<Client> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testClientList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testClientList.first(), result.first())
    }

    @Test
    fun `should return clients by ID`() {
        val iterable: FindIterable<Client> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testClient)

        val result = dao.findByID("id-test-bson")
        assertEquals(testClient, result)
    }

    @Test
    fun `should return clients by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Client> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testClientList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one client`() {
        val result = dao.insertOne(testClient)
        assertEquals(testClient, result)
    }

    @Test
    fun `should insert many clients`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testClientList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testClientList)
        verify(collection, times(1)).insertMany(eq(testClientList), any())
    }

    @Test
    fun `should update one client`() {

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
                update = eq(Document("\$set", testClient)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testClient)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testClient)),
                options = any()
            )
    }

    @Test
    fun `should update many clients`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        val updateBson = testClientList.map { Document("\$set", it) }.toList()

        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testClientList)
        assertEquals(testClientList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateBson = testClientList.map { Document("\$set", it) }.toList()

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testClientList)
        }
    }

    @Test
    fun `should delete one client`() {

        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("id-test-bson")
        assertTrue(result)
    }

    @Test
    fun `should delete many clients`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", "id-test-bson"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("id-test-bson"))
        assertTrue(result)
    }
}