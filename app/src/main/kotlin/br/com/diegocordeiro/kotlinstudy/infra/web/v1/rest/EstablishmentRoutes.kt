package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.EstablishmentUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.CreateEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.DeleteEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.GetEstablishmentUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.establishment.UpdateEstablishmentUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.establishmentRoutes() {

    val logger = LoggerFactory.getLogger("Establishment Route")

    val getEstablishmentUseCase: GetEstablishmentUseCase by inject()
    val createEstablishmentUseCase: CreateEstablishmentUseCase by inject()
    val updateEstablishmentUseCase: UpdateEstablishmentUseCase by inject()
    val deleteEstablishmentUseCase: DeleteEstablishmentUseCase by inject()

    route("/v1/establishment") {
        get("/{id}") {
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/establishment/$id")
            call.respond(
                message = getEstablishmentUseCase.execute(id = id.orEmpty()),
                status = HttpStatusCode.OK
            )
            logger.info("START - GET - /v1/establishment/$id")
        }
        get {
            logger.info("START - GET - /v1/establishment")
            call.respond(
                message = getEstablishmentUseCase.execute(),
                status = HttpStatusCode.OK
            )
            logger.info("START - GET - /v1/establishment")
        }
        post {
            logger.info("START - POST - /v1/establishment")
            createEstablishmentUseCase.execute(
                call.receive<EstablishmentCreateInput>()
            )
            call.respond(
                HttpStatusCode.Created
            )
            logger.info("START - POST - /v1/establishment")
        }
        put {
            logger.info("START - PUT - /v1/establishment")
            val establishmentUpdateInput = updateEstablishmentUseCase.execute(
                call.receive<EstablishmentUpdateInput>()
            )
            call.respond(
                message = establishmentUpdateInput,
                status = HttpStatusCode.OK
            )
            logger.info("START - PUT - /v1/establishment")
        }
        delete("/{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/establishment/$id")
            val establishmentDeleteInput = deleteEstablishmentUseCase.execute(
                EstablishmentDeleteInput(
                    id = id.orEmpty()
                )
            )
            call.respond(
                message = establishmentDeleteInput,
                status = HttpStatusCode.OK
            )
            logger.info("START - DELETE - /v1/establishment/$id")
        }
    }
}