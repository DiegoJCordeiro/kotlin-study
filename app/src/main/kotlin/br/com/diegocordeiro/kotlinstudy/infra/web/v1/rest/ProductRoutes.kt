package br.com.diegocordeiro.kotlinstudy.infra.web.v1.rest

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.*
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.CreateProductUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.DeleteProductUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.GetProductUseCase
import br.com.diegocordeiro.kotlinstudy.application.usecases.product.UpdateProductUseCase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.productRoutes() {

    val logger = LoggerFactory.getLogger("Product Route")

    val getProductUseCase: GetProductUseCase by inject()
    val createProductUseCase: CreateProductUseCase by inject()
    val updateProductUseCase: UpdateProductUseCase by inject()
    val deleteProductUseCase: DeleteProductUseCase by inject()

    route("/v1/product") {
        get("{id}") {
            val id = call.parameters["id"]
            logger.info("START - GET - /v1/product/$id")
            call.respond(
                message = getProductUseCase.execute(id = id.orEmpty()),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/product/$id")
        }
        get() {
            logger.info("START - GET - /v1/product")
            call.respond(
                message = getProductUseCase.execute(),
                status = HttpStatusCode.OK
            )
            logger.info("END - GET - /v1/product")
        }
        post() {
            logger.info("START - POST - /v1/product")
            createProductUseCase.execute(
                call.receive<ProductCreateInput>()
            )
            call.respond(
                HttpStatusCode.Created
            )
            logger.info("END - POST - /v1/product")
        }
        put() {
            logger.info("START - PUT - /v1/product")
            val productUpdateInput = updateProductUseCase.execute(
                call.receive<ProductUpdateInput>()
            )
            call.respond(
                message = productUpdateInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - PUT - /v1/product")
        }
        delete("{id}") {
            val id = call.parameters["id"]
            logger.info("START - DELETE - /v1/product/$id")
            val productDeleteInput = deleteProductUseCase.execute(
                ProductDeleteInput(
                    id = id.orEmpty()
                )
            )
            call.respond(
                message = productDeleteInput,
                status = HttpStatusCode.OK
            )
            logger.info("END - DELETE - /v1/product/$id")
        }
    }
}