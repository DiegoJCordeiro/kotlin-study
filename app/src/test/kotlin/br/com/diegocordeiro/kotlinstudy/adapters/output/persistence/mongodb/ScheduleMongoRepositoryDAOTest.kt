package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Schedule
import br.com.diegocordeiro.kotlinstudy.domain.model.*
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.ScheduleMongoRepositoryDAO
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

class ScheduleMongoRepositoryDAOTest {

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Schedule>
    private lateinit var dao: ScheduleMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testSchedule: Schedule = createSchedule()
    private val testScheduleList: List<Schedule> = createScheduleList()

    companion object {
        private const val COLLECTION_NAME = "schedules"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Schedule>(COLLECTION_NAME)).thenReturn(collection)
        dao = ScheduleMongoRepositoryDAO(mongoDatabase)
    }

    @Test
    fun `should return all schedules`() {
        val iterable: FindIterable<Schedule> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testScheduleList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testScheduleList.first(), result.first())
    }

    @Test
    fun `should return schedule by ID`() {
        val iterable: FindIterable<Schedule> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testSchedule)

        val result = dao.findByID("id-test-bson")
        assertEquals(testSchedule, result)
    }

    @Test
    fun `should return schedules by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Schedule> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testScheduleList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one schedule`() {
        val result = dao.insertOne(testSchedule)
        assertEquals(testSchedule, result)
    }

    @Test
    fun `should insert many schedules`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testScheduleList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testScheduleList)
        verify(collection, times(1)).insertMany(eq(testScheduleList), any())
    }

    @Test
    fun `should update one schedule`() {

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
                update = eq(Document("\$set", testSchedule)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testSchedule)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testSchedule)),
                options = any()
            )
    }

    @Test
    fun `should update many schedules`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        val updateBson = testScheduleList.map { Document("\$set", it) }.toList()

        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testScheduleList)
        assertEquals(testScheduleList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateBson = testScheduleList.map { Document("\$set", it) }.toList()

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testScheduleList)
        }
    }

    @Test
    fun `should delete one schedule`() {

        val bsonFilter = Filters.and(Filters.eq("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("6839c0fb77889f4e558fd393")
        assertTrue(result)
    }

    @Test
    fun `should delete many schedules`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("6839c0fb77889f4e558fd393"))
        assertTrue(result)
    }
}