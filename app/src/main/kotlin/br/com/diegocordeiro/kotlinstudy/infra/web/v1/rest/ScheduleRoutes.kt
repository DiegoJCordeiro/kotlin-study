package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ScheduleUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.CreateScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.DeleteScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.GetScheduleUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.schedule.UpdateScheduleUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.scheduleRoutes() {

    val logger = LoggerFactory.getLogger("Schedule Route")

    val getScheduleUseCase: GetScheduleUseCase by inject()
    val createScheduleUseCase: CreateScheduleUseCase by inject()
    val updateScheduleUseCase: UpdateScheduleUseCase by inject()
    val deleteScheduleUseCase: DeleteScheduleUseCase by inject()

    route("/v1/schedule") {
        get("{id}") {
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/schedule/$id")
            call.respond(
                message = getScheduleUseCase.execute(id = id.orEmpty()),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/schedule/$id")
        }
        get {
            logger.info("START - GET - /v1/schedule")
            call.respond(
                message = getScheduleUseCase.execute(),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/schedule")
        }
        post {
            logger.info("START - POST - /v1/schedule")
            createScheduleUseCase.execute(
                call.receive<ScheduleCreateInput>()
            )
            call.respond(
                HttpStatusCode.Created
            )
            logger.info("END - POST - /v1/schedule")
        }
        put {
            logger.info("START - PUT - /v1/schedule")
            val scheduleUpdateInput = updateScheduleUseCase.execute(
                call.receive<ScheduleUpdateInput>()
            )
            call.respond(
                message = scheduleUpdateInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - PUT - /v1/schedule")
        }
        delete("{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/schedule/$id")
            val scheduleDeleteInput = deleteScheduleUseCase.execute(
                ScheduleDeleteInput(
                    id = id.orEmpty()
                )
            )
            call.respond(
                message = scheduleDeleteInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - DELETE - /v1/schedule/$id")
        }
    }
}