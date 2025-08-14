package br.com.diegocordeiro.kotlinstudy.adapters.output.persistence.mongodb

import br.com.diegocordeiro.kotlinstudy.domain.models.Plan
import br.com.diegocordeiro.kotlinstudy.domain.model.createPlan
import br.com.diegocordeiro.kotlinstudy.domain.model.createPlanList
import br.com.diegocordeiro.kotlinstudy.infra.repositories.mongodb.PlanMongoRepositoryDAO
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

class PlansMongoRepositoryDAOTest {

    private lateinit var mongoDatabase: MongoDatabase
    private lateinit var collection: MongoCollection<Plan>
    private lateinit var dao: PlanMongoRepositoryDAO
    private lateinit var insertResult: InsertManyResult

    private val testPlan: Plan = createPlan()
    private val testPlanList: List<Plan> = createPlanList()

    companion object {
        private const val COLLECTION_NAME = "plans"
    }

    @BeforeEach
    fun setup() {
        insertResult = mock()
        mongoDatabase = mock()
        collection = mock()
        whenever(mongoDatabase.getCollection<Plan>(COLLECTION_NAME)).thenReturn(collection)
        dao = PlanMongoRepositoryDAO(mongoDatabase)
    }

    @Test
    fun `should return all plans`() {
        val iterable: FindIterable<Plan> = mock()
        whenever(collection.find()).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testPlanList)

        val result = dao.findAll()
        assertEquals(1, result.size)
        assertEquals(testPlanList.first(), result.first())
    }

    @Test
    fun `should return plan by ID`() {
        val iterable: FindIterable<Plan> = mock()
        whenever(collection.find(Filters.and(Filters.eq("_id", "id-test-bson")))).thenReturn(iterable)
        whenever(iterable.firstOrNull()).thenReturn(testPlan)

        val result = dao.findByID("id-test-bson")
        assertEquals(testPlan, result)
    }

    @Test
    fun `should return plans by filter`() {
        val filters = mapOf("city" to "Test City")
        val filtersBson = filters.map { (field, value) ->
            Filters.eq(field, value)
        }

        val iterable: FindIterable<Plan> = mock()
        whenever(collection.find(Filters.and(filtersBson))).thenReturn(iterable)
        whenever(iterable.toList()).thenReturn(testPlanList)

        val result = dao.findBy(filters)
        assertEquals(1, result.size)
    }

    @Test
    fun `should insert one plan`() {
        val result = dao.insertOne(testPlan)
        assertEquals(testPlan, result)
    }

    @Test
    fun `should insert many plan`() {

        val insertedManyResult = mock<InsertManyResult> {
            on { wasAcknowledged() } doReturn true
        }

        whenever(collection.insertMany(eq(testPlanList), any())).thenReturn(insertedManyResult)
        whenever(insertedManyResult.wasAcknowledged()).thenReturn(true)
        dao.insertMany(testPlanList)
        verify(collection, times(1)).insertMany(eq(testPlanList), any())
    }

    @Test
    fun `should update one plan`() {

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
                update = eq(Document("\$set", testPlan)),
                options = any()
            )).thenReturn(updateResult)

        dao.updateOne(filters, testPlan)

        verify(collection, times(1))
            .updateOne(
                filter = eq(bsonFilter),
                update = eq(Document("\$set", testPlan)),
                options = any()
            )
    }

    @Test
    fun `should update many plans`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateResult: UpdateResult = mock()
        val updateBson = testPlanList.map { Document("\$set", it) }.toList()

        whenever(updateResult.matchedCount).thenReturn(1L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )
        ).thenReturn(updateResult)

        val result = dao.updateMany(filters, testPlanList)
        assertEquals(testPlanList, result)
    }

    @Test
    fun `should throw exception when update many finds nothing`() {

        val filters = mapOf("_id" to "id-test-bson")
        val bsonFilter = Filters.and(Filters.eq("_id", "id-test-bson"))

        val updateBson = testPlanList.map { Document("\$set", it) }.toList()

        val updateResult: UpdateResult = mock()
        whenever(updateResult.matchedCount).thenReturn(0L)
        whenever(collection.updateMany(
            filter = eq(bsonFilter),
            update = eq(updateBson),
            options = any()
        )).thenReturn(updateResult)

        assertThrows<NoSuchElementException> {
            dao.updateMany(filters, testPlanList)
        }
    }

    @Test
    fun `should delete one plan`() {

        val bsonFilter = Filters.and(Filters.eq("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(1L)
        whenever(collection.deleteOne(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteOne("6839c0fb77889f4e558fd393")
        assertTrue(result)
    }

    @Test
    fun `should delete many plans`() {

        val bsonFilter = Filters.and(Filters.`in`("_id", "6839c0fb77889f4e558fd393"))

        val deleteResult: DeleteResult = mock()
        whenever(deleteResult.deletedCount).thenReturn(2L)
        whenever(collection.deleteMany(eq(bsonFilter), any())).thenReturn(deleteResult)

        val result = dao.deleteMany(listOf("6839c0fb77889f4e558fd393"))
        assertTrue(result)
    }
}