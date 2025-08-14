package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ClientUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.CreateClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.DeleteClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.GetClientUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.client.UpdateClientUseCase

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.delete

import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.clientRoutes() {

    val logger = LoggerFactory.getLogger("Client Route")

    val createClientUseCase: CreateClientUseCase by inject()
    val updateClientUseCase: UpdateClientUseCase by inject()
    val deleteClientUseCase: DeleteClientUseCase by inject()
    val getClientUseCase: GetClientUseCase by inject()

    route("/v1/client") {
        get("/{id}") {
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/client/$id")
            val clientsFound = getClientUseCase.execute(id = id.toString())
            call.respond(
                message = clientsFound,
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/client/$id")
        }
        get {
            logger.info("START - GET - /v1/client")
            val clientsFound = getClientUseCase.execute()
            call.respond(
                message = clientsFound,
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/client")
        }
        post {
            logger.info("START - POST - /v1/client")
            val clientCreateInput = createClientUseCase.execute(
                call.receive<ClientCreateInput>()
            )
            call.respond(
                message = clientCreateInput,
                status = HttpStatusCode.Created
            )
            logger.info("END - POST - /v1/client")
        }
        put {
            logger.info("START - PUT - /v1/client")
            val clientUpdateInput = updateClientUseCase.execute(
                call.receive<ClientUpdateInput>()
            )
            call.respond(
                message = clientUpdateInput,
                status = HttpStatusCode.Created
            )
            logger.info("END - PUT - /v1/client")
        }
        delete("/{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/client/$id")
            val clientDeleteInput = deleteClientUseCase.execute(
                ClientDeleteInput(id = id.toString())
            )
            call.respond(clientDeleteInput)
            logger.info("END - DELETE - /v1/client/$id")
        }
    }
}