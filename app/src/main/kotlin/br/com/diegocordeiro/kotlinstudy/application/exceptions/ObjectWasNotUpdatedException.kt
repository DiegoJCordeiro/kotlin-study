package br.com.diegocordeiro.kotlinstudy.application.exceptions

sealed class ObjectWasNotUpdatedException(override val message: String?): RuntimeException(message)