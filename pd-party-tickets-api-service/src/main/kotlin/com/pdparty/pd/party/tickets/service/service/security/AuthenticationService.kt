package com.pdparty.pd.party.tickets.service.service.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.pdparty.pd.party.tickets.service.model.User
import com.pdparty.pd.party.tickets.service.service.UserService
import java.time.Duration
import java.time.Instant
import java.util.*

class AuthenticationService(private val userService: UserService) {
  suspend fun authenticate(request: AuthRequest): User? {
    var user = userService.find(request.username)
    require(user != null) { "The user doen't exists." }

    user.let {
      if (Hasher.hashPassword(request.password, it.salt) != it.password)
        user = null
    }

    return user
  }

  fun createToken(user: User): String =
    JWT.create()
      .withIssuer(ISSUER)
      .withClaim("username", user.username)
      .withExpiresAt(Date.from(Instant.now().plus(TOKEN_TTL)))
      .sign(TOKEN_ALGORITHM)

  suspend fun createSecurityContext(jwtToken: String): SecurityContext? =
    decodeToken(jwtToken)
      ?.let { userService.find(it.getClaim("username").asString()) }
      ?.let { user -> SecurityContext(user) }

  private fun decodeToken(jwtToken: String): DecodedJWT? =
    try {
      DECODER.verify(jwtToken)
    } catch (err: JWTVerificationException) {
      null
    }

  private companion object {
    const val ISSUER = "pd-party-tickets"
    val TOKEN_TTL: Duration = Duration.ofDays(30)
    val TOKEN_ALGORITHM: Algorithm = Algorithm.HMAC512(JWT_SECRET)
    val DECODER: JWTVerifier = (JWT.require(TOKEN_ALGORITHM) as JWTVerifier.BaseVerification).build(JWTClock())
  }

  private class JWTClock : com.auth0.jwt.interfaces.Clock {
    override fun getToday(): Date = Date.from(Instant.now())
  }
}