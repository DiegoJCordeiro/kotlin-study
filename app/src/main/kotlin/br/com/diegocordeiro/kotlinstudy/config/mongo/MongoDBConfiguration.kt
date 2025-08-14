package br.com.diegocordeiro.kotlinstudy.config.mongo

import com.mongodb.kotlin.client.MongoClient

class MongoDBConfiguration(
    private val uri: String = "mongodb://localhost:27017",
    private val database: String
) {
    fun getDatabase() = MongoClient.create(uri).getDatabase(database)
}