package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.AddressUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.CreateAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.DeleteAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.GetAddressUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.address.UpdateAddressUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.addressRoutes() {

    val logger = LoggerFactory.getLogger("Address Route")

    val getAddressUseCase: GetAddressUseCase by inject()
    val createAddressUseCase: CreateAddressUseCase by inject()
    val updateAddressUseCase: UpdateAddressUseCase by inject()
    val deleteAddressUseCase: DeleteAddressUseCase by inject()

    route("/v1/address") {
        get {
            logger.info("START - GET - /v1/address")
            call.respond(
                getAddressUseCase.execute()
            )
            logger.info("END - GET -  /v1/address")
        }
        get("/{id}"){
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/address/$id")
            call.respond(
                getAddressUseCase.execute(id = id.orEmpty())
            )
            logger.info("END - GET - /v1/address/ $id")
        }
        post {
            logger.info("START - POST - /v1/address")
            val addressCreateInput = createAddressUseCase.execute(
                call.receive<AddressCreateInput>()
            )
            call.respond(
                message = addressCreateInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - POST - /v1/address")
        }
        put {
            logger.info("START - PUT - /v1/address")
            val addressUpdateInput = updateAddressUseCase.execute(
                call.receive<AddressUpdateInput>()
            )
            call.respond(addressUpdateInput)
            logger.info("END - PUT - /v1/address")
        }
        delete("/{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/address/$id")
            val addressDeleteInput = deleteAddressUseCase.execute(
                AddressDeleteInput(id = id.toString())
            )
            call.respond(addressDeleteInput)
            logger.info("END - DELETE - /v1/address/$id")
        }
    }
}