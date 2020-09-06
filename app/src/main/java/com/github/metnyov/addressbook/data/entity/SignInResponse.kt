package com.github.metnyov.addressbook.data.entity

import com.squareup.moshi.Json

data class SignInResponse(

	@Json(name = "Message")
	val message: String? = null,

	@Json(name = "Success")
	val success: Boolean? = null
)