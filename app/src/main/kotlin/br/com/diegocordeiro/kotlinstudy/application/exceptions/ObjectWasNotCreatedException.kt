package br.com.diegocordeiro.kotlinstudy.application.exceptions

sealed class ObjectWasNotCreatedException(override val message: String?): RuntimeException(message)