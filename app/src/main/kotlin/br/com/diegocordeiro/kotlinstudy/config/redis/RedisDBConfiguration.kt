package br.com.diegocordeiro.kotlinstudy.config.redis

import redis.clients.jedis.UnifiedJedis

class RedisDBConfiguration(
    private val uri: String = "redis://localhost:27017"
) {
    fun getDatabase() = UnifiedJedis(uri)
}