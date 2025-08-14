package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Address
import br.com.diegocordeiro.kotlinstudy.domain.model.createAddress
import br.com.diegocordeiro.kotlinstudy.domain.model.createAddressList
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.AddressMongoRepositoryDAO
import com.mongodb.client.model.Filters
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertManyResult
import com.mongodb.client.result.UpdateResult
import com.mongodb.kotlin.client.FindIterable
import com.mongodb.kotlin.client.MongoDatabase
import com.mongodb.kotlin.client.MongoCollection
import org.bson.Document
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class AddressMongoRepositoryDAOTest {

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Address>
    private lateinit var dao: AddressMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testAddress: Address = createAddress()
    private val testAddressList: List<Address> = createAddressList()

    companion object {
        private const val COLLECTION_NAME = "addresses"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Address>(COLLECTION_NAME)).thenReturn(collection)
        dao = AddressMongoRepositoryDAO(mongoDatabase)
    }

    @Test
    fun `should return all addresses`() {
        val iterable: FindIterable<Address> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testAddressList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testAddressList.first(), result.first())
    }

    @Test
    fun `should return address by ID`() {
        val iterable: FindIterable<Address> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testAddress)

        val result = dao.findByID("id-test-bson")
        assertEquals(testAddress, result)
    }

    @Test
    fun `should return addresses by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Address> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testAddressList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one address`() {
        val result = dao.insertOne(testAddress)
        assertEquals(testAddress, result)
    }

    @Test
    fun `should insert many addresses`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testAddressList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testAddressList)
        verify(collection, times(1)).insertMany(eq(testAddressList), any())
    }

    @Test
    fun `should update one address`() {

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
                update = eq(Document("\$set", testAddress)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testAddress)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testAddress)),
                options = any()
            )
    }

    @Test
    fun `should throw exception when update one finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(
            collection.updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testAddress)),
                options = any()
            )
        ).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateOne(filters, testAddress)
        }
    }

    @Test
    fun `should update many addresses`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testAddressList)),
                options = any()
            )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testAddressList)
        assertEquals(testAddressList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(Document("\$set", testAddressList)),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testAddressList)
        }
    }

    @Test
    fun `should delete one address`() {

        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("id-test-bson")
        assertTrue(result)
    }

    @Test
    fun `should delete many addresses`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", ObjectId("6839c0fb77889f4e558fd393")))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("6839c0fb77889f4e558fd393"))
        assertTrue(result)
    }
}