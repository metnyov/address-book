package com.github.metnyov.addressbook.data.dao

import com.github.metnyov.addressbook.domain.source.AuthDao
import com.github.metnyov.addressbook.domain.source.Preferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AuthDaoImpl(private val preferences: Preferences) : AuthDao {

    override var username: String by PreferencesDelegate(USERNAME_KEY, "")
    override var password: String by PreferencesDelegate(PASSWORD_KEY, "")

    override fun login(username: String, password: String) {
        this.username = username
        this.password = password
    }

    override fun logout() {
        username = ""
        password = ""
    }

    class PreferencesDelegate<T : Any>(
        private val key: String,
        private val default: T
    ) : ReadWriteProperty<AuthDaoImpl, T> {

        override fun getValue(thisRef: AuthDaoImpl, property: KProperty<*>): T {
            return thisRef.preferences.get(key, default)
        }

        override fun setValue(thisRef: AuthDaoImpl, property: KProperty<*>, value: T) {
            thisRef.preferences.put(key, value)
        }
    }

    companion object {
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
    }
}