package com.data

import java.io.Serializable

data class CastList(
  val id :Int,
    val name:String,
    val character:String,
    val profile_path:String
): Serializable
