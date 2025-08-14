package br.com.diegocordeiro.kotlinstudy.application.usecases.phone

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.PhoneDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneCreateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.PhoneDeleteOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoPhoneCRUDRepository

interface GetPhoneUseCase {

    val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository

    fun execute(id: String = ""): List<PhoneCreateOutput>
}

interface CreatePhoneUseCase {

    val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository

    fun execute(phoneCreateInput: PhoneCreateInput): PhoneCreateOutput
}

interface UpdatePhoneUseCase {

    val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository

    fun execute(phoneUpdateInput: PhoneUpdateInput): PhoneUpdateOutput
}

interface DeletePhoneUseCase {

    val phoneMongoRepositoryDAO: MongoPhoneCRUDRepository

    fun execute(phoneDeleteInput: PhoneDeleteInput): PhoneDeleteOutput
}