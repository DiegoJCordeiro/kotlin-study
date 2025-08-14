package br.com.diegocordeiro.kotlinstudy.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parameter(
    @SerialName("parameterName")
    val parameterName: String?,
    @SerialName("parameterValue")
    val parameterValue: String?,
)
