package br.com.diegocordeiro.kotlinstudy.application.exceptions

sealed class ObjectWasNotFoundException(override val message: String?): RuntimeException(message)