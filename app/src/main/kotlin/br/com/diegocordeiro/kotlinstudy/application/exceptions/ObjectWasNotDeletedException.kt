package br.com.diegocordeiro.kotlinstudy.application.exceptions

class ObjectWasNotDeletedException(override val message: String?): RuntimeException(message)