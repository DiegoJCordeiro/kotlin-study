package br.com.diegocordeiro.kotlinstudy

import br.com.diegocordeiro.kotlinstudy.config.ktor.configureContentNegotiation
import br.com.diegocordeiro.kotlinstudy.config.koin.configureDependencyInjection
import br.com.diegocordeiro.kotlinstudy.config.ktor.configureErrorHandler
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.addressRoutes
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.clientRoutes
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.establishmentRoutes
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.phoneRoutes
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.planRoutes
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.productRoutes
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.professionalRoutes
import br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest.scheduleRoutes

import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun main(){

    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {

    configureContentNegotiation()
    configureErrorHandler()
    configureDependencyInjection()

    routing {
        swaggerUI(path = "swagger", swaggerFile = "swagger/documentation.yaml")
        addressRoutes()
        clientRoutes()
        establishmentRoutes()
        phoneRoutes()
        planRoutes()
        productRoutes()
        professionalRoutes()
        scheduleRoutes()
    }
}