package com.pdparty.pd.party.tickets.service.service.security

import com.pdparty.pd.party.tickets.service.model.User
import com.typesafe.config.ConfigFactory
import java.security.MessageDigest

val JWT_SECRET: String = ConfigFactory.load().getConfig("authentication").getString("secret")

data class SecurityContext(val user: User)

data class AuthRequest(val username: String, val password: String)

object Hasher {
  fun hashPassword(password: String, salt: String): String =
    hashString(hashString(password, "SHA-256") + hashString(salt, "SHA-512"), "SHA-1")

  private fun hashString(toHash: String, algorithm: String): String {
    val bytes = toHash.toByteArray()
    val md = MessageDigest.getInstance(algorithm)
    val digest = md.digest(bytes)
    return digest.fold("") { str, it ->
      str + "%02x".format(it)
    }
  }
}