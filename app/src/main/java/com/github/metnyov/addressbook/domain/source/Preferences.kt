package com.github.metnyov.addressbook.domain.source

interface Preferences {

    fun <T : Any> get(key: String): T?

    fun <T : Any> get(key: String, default: T): T

    fun <T : Any> put(key: String, value: T): Boolean

    fun delete(key: String): Boolean

    fun contains(key: String): Boolean
}