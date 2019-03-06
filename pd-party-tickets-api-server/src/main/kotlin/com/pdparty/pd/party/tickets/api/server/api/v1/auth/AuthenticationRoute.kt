package com.pdparty.pd.party.tickets.api.server.api.v1.auth

import com.pdparty.pd.party.tickets.api.server.middleware.authenticationMiddleware
import com.pdparty.pd.party.tickets.service.service.security.AuthRequest
import com.pdparty.pd.party.tickets.service.service.security.AuthenticationService
import com.pdparty.pd.party.tickets.service.service.security.SignupRequest
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.auth(authenticationService: AuthenticationService) {
  route("auth") {
    post("login") {
      val authRequest = call.receive<AuthRequest>()
      val user = authenticationService.authenticate(authRequest)

      if (user != null)
        call.respond(authenticationService.createToken(user))
      else
        call.respond(HttpStatusCode.Forbidden)
    }

    route("signup") {
      authenticationMiddleware(authenticationService)

      post {
        val signupRequest = call.receive<SignupRequest>()
        authenticationService.signup(signupRequest)

        call.respond(HttpStatusCode.Created)
      }
    }
  }
}