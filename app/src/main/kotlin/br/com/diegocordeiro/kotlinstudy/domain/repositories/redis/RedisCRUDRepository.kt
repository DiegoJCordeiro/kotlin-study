package br.com.diegocordeiro.kotlinstudy.domain.repositories.redis

import br.com.diegocordeiro.kotlinstudy.domain.models.Address
import br.com.diegocordeiro.kotlinstudy.domain.models.Client

sealed interface RedisCRUDRepository<T, ID> {
    fun set(key: ID, model: T)
    fun getBy(vararg keys: ID)
    fun getByKey(key: ID)
    fun deleteByKey(key: ID)
    fun deleteBy(vararg keys: ID)
}

interface RedisAddressRepositoryImpl: RedisCRUDRepository<Address, String> { }

interface RedisClientRepositoryImpl: RedisCRUDRepository<Client, String> { }

interface RedisCouponRepositoryImpl: RedisCRUDRepository<Client, String> { }

interface RedisEstablishmentRepositoryImpl: RedisCRUDRepository<Client, String> { }

interface RedisPhoneRepositoryImpl: RedisCRUDRepository<Client, String> { }

interface RedisProductRepositoryImpl: RedisCRUDRepository<Client, String> { }

interface RedisProfessionalRepositoryImpl: RedisCRUDRepository<Client, String> { }

interface RedisScheduleRepositoryImpl: RedisCRUDRepository<Client, String> { }