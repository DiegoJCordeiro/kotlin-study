package br.com.diegocordeiro.kotlinstudy.domain.model

import br.com.diegocordeiro.kotlinstudy.domain.models.Schedule
import java.time.LocalDateTime

fun createSchedule(): Schedule = Schedule(
    id = "id-test-bson",
    professional = "id-professional-test",
    establishment = "id-establishment-test",
    products = emptyList(),
    dateTimeSchedule = LocalDateTime.now()
)

fun createScheduleList(): List<Schedule> = listOf(
    createSchedule()
)