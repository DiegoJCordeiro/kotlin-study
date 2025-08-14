package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.*
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.CreatePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.DeletePhoneUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.GetPhoneUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.phone.UpdatePhoneUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.phoneRoutes() {

    val logger = LoggerFactory.getLogger("Phone Route")

    val getPhoneUseCase: GetPhoneUseCase by inject()
    val createPhoneUseCase: CreatePhoneUseCase by inject()
    val updatePhoneUseCase: UpdatePhoneUseCase by inject()
    val deletePhoneUseCase: DeletePhoneUseCase by inject()

    route("/v1/phone") {
        get("{id}") {
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/phone/$id")
            call.respond(
                message = getPhoneUseCase.execute(id = id.orEmpty()),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/phone/$id")
        }
        get {
            logger.info("START - GET - /v1/phone")
            call.respond(
                message = getPhoneUseCase.execute(),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/phone")
        }
        post {
            logger.info("START - POST - /v1/phone")
            createPhoneUseCase.execute(
                call.receive<PhoneCreateInput>()
            )
            call.respond(
                HttpStatusCode.Created
            )
            logger.info("END - POST - /v1/phone")
        }
        put {
            logger.info("START - PUT - /v1/phone")
            val phoneUpdateInput = updatePhoneUseCase.execute(
                call.receive<PhoneUpdateInput>()
            )
            call.respond(
                message = phoneUpdateInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - PUT - /v1/phone")
        }
        delete("{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/phone/$id")
            val phoneDeleteInput = deletePhoneUseCase.execute(
                PhoneDeleteInput(
                    id = id.orEmpty()
                )
            )
            call.respond(
                message = phoneDeleteInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - DELETE - /v1/phone/$id")
        }
    }
}