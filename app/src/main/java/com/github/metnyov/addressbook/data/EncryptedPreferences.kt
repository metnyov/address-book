package com.github.metnyov.addressbook.data

import com.github.metnyov.addressbook.domain.source.Preferences
import com.orhanobut.hawk.Hawk

class EncryptedPreferences : Preferences {

    override fun <T : Any> get(key: String): T? = Hawk.get(key)

    override fun <T : Any> get(key: String, default: T): T = Hawk.get(key, default)

    override fun <T : Any> put(key: String, value: T): Boolean = Hawk.put(key, value)

    override fun delete(key: String) = Hawk.delete(key)

    override fun contains(key: String): Boolean = Hawk.contains(key)
}