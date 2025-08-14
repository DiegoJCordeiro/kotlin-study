package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.*
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.CreatePlanUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.DeletePlanUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.GetPlanUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.plan.UpdatePlanUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory


fun Route.planRoutes() {

    val logger = LoggerFactory.getLogger("Plan Route")

    val getPlanUseCase: GetPlanUseCase by inject()
    val createPlanUseCase: CreatePlanUseCase by inject()
    val updatePlanUseCase: UpdatePlanUseCase by inject()
    val deletePlanUseCase: DeletePlanUseCase by inject()

    route("/v1/plan") {
        get("{id}") {
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/plan/$id")
            call.respond(
                message = getPlanUseCase.execute(id = id.orEmpty()),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/plan/$id")
        }
        get {
            logger.info("START - GET - /v1/plan")
            call.respond(
                message = getPlanUseCase.execute(),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/plan")
        }
        post {
            logger.info("START - POST - /v1/plan")
            createPlanUseCase.execute(
                call.receive<PlanCreateInput>()
            )
            call.respond(
                HttpStatusCode.Created
            )
            logger.info("END - POST - /v1/plan")
        }
        put {
            logger.info("START - PUT - /v1/plan")
            val planUpdateInput = updatePlanUseCase.execute(
                call.receive<PlanUpdateInput>()
            )
            call.respond(
                message = planUpdateInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - PUT - /v1/plan")
        }
        delete("{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/plan/$id")
            val planDeleteInput = deletePlanUseCase.execute(
                PlanDeleteInput(
                    id = id.orEmpty()
                )
            )
            call.respond(
                message = planDeleteInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - DELETE - /v1/plan/$id")
        }
    }
}