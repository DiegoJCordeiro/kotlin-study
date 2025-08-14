package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Coupon
import br.com.diegocordeiro.kotlinstudy.domain.model.createCoupon
import br.com.diegocordeiro.kotlinstudy.domain.model.createCouponList
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.CouponMongoRepositoryDAO
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

class CouponMongoRepositoryDAOTest {

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Coupon>
    private lateinit var dao: CouponMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testCoupon: Coupon = createCoupon()
    private val testCouponList: List<Coupon> = createCouponList()

    companion object {
        private const val COLLECTION_NAME = "coupons"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Coupon>(COLLECTION_NAME)).thenReturn(collection)
        dao = CouponMongoRepositoryDAO(mongoDatabase)
    }

    @Test
    fun `should return all coupons`() {
        val iterable: FindIterable<Coupon> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testCouponList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testCouponList.first(), result.first())
    }

    @Test
    fun `should return coupons by ID`() {
        val iterable: FindIterable<Coupon> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testCoupon)

        val result = dao.findByID("id-test-bson")
        assertEquals(testCoupon, result)
    }

    @Test
    fun `should return coupons by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Coupon> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testCouponList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one coupon`() {
        val result = dao.insertOne(testCoupon)
        assertEquals(testCoupon, result)
    }

    @Test
    fun `should insert many coupons`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testCouponList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testCouponList)
        verify(collection, times(1)).insertMany(eq(testCouponList), any())
    }

    @Test
    fun `should update one coupon`() {

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
                update = eq(Document("\$set", testCoupon)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testCoupon)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testCoupon)),
                options = any()
            )
    }

    @Test
    fun `should update many coupons`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        val updateBson = testCouponList.map { Document("\$set", it) }.toList()

        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testCouponList)
        assertEquals(testCouponList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateBson = testCouponList.map { Document("\$set", it) }.toList()

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testCouponList)
        }
    }

    @Test
    fun `should delete one coupon`() {

        val bsonFilter = Filters.and(Filters.eq("_id", ObjectId("60b8d2b5f0e4e4001c8b4567")))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("60b8d2b5f0e4e4001c8b4567")
        assertTrue(result)
    }

    @Test
    fun `should delete many coupon`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", ObjectId("6839c0fb77889f4e558fd393")))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("6839c0fb77889f4e558fd393"))
        assertTrue(result)
    }
}