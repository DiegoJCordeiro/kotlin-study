package br.com.diegocordeiro.kotlinstudy.domain.enums

enum class SettingsType(
    val value: String,
    val description: String,
) {
    PRODUCT("P", "product_settings"),
    SCHEDULE("S", "schedule_settings"),
    COURSE("C", "course_settings")
}