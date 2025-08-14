package br.com.diegocordeiro.kotlinstudy.domain.enums

enum class PhoneType (
    val value: String,
    val description: String,
){
    CELLPHONE("cellphone", "It's a cellphone"),
    TELEPHONE("telephone", "It's a cellphone"),
    FAX("fax", "It's a fax"),
    OTHER("other", "It's a other phone type"),
}