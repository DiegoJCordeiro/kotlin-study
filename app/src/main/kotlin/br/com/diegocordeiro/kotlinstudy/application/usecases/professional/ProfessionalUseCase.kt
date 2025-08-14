package br.com.diegocordeiro.kotlinstudy.application.usecases.professional

import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalDeleteInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalUpdateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.inbound.ProfessionalCreateInput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalDeleteOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalUpdateOutput
import br.com.diegocordeiro.kotlinstudy.application.dto.outbound.ProfessionalCreateOutput
import br.com.diegocordeiro.kotlinstudy.domain.repositories.mongodb.MongoProfessionalCRUDRepository

interface GetProfessionalUseCase {
    val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
    fun execute(id: String = ""): List<ProfessionalCreateOutput>
}

interface CreateProfessionalUseCase {
    val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
    fun execute(professionalCreateInput: ProfessionalCreateInput): ProfessionalCreateOutput
}

interface UpdateProfessionalUseCase {
    val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
    fun execute(professionalUpdateInput: ProfessionalUpdateInput): ProfessionalUpdateOutput
}

interface DeleteProfessionalUseCase {
    val professionalMongoRepositoryDAO: MongoProfessionalCRUDRepository
    fun execute(professionalDeleteInput: ProfessionalDeleteInput): ProfessionalDeleteOutput
}