package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.*
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.CreateProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.DeleteProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.GetProfessionalUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.professional.UpdateProfessionalUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.professionalRoutes() {

    val logger = LoggerFactory.getLogger("Professional Route")

    val getProfessionalUseCase: GetProfessionalUseCase by inject()
    val createProfessionalUseCase: CreateProfessionalUseCase by inject()
    val updateProfessionalUseCase: UpdateProfessionalUseCase by inject()
    val deleteProfessionalUseCase: DeleteProfessionalUseCase by inject()

    route("/v1/professional") {
        get("{id}") {
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/professional/$id")
            call.respond(
                message = getProfessionalUseCase.execute(id = id.orEmpty()),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/professional/$id")
        }
        get {
            logger.info("START - GET - /v1/professional")
            call.respond(
                message = getProfessionalUseCase.execute(),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/professional")
        }
        post {
            logger.info("START - POST - /v1/professional")
            createProfessionalUseCase.execute(
                call.receive<ProfessionalCreateInput>()
            )
            call.respond(
                HttpStatusCode.Created
            )
            logger.info("END - POST - /v1/professional")
        }
        put {
            logger.info("START - PUT - /v1/professional")
            val professionalUpdateInput = updateProfessionalUseCase.execute(
                call.receive<ProfessionalUpdateInput>()
            )
            call.respond(
                message = professionalUpdateInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - PUT - /v1/professional")
        }
        delete("{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/professional/$id")
            val professionalDeleteInput = deleteProfessionalUseCase.execute(
                ProfessionalDeleteInput(
                    id = id.orEmpty()
                )
            )
            call.respond(
                message = professionalDeleteInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - DELETE - /v1/professional/$id")
        }
    }
}