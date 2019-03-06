package com.pdparty.pd.party.tickets.api.server.api.v1.user

import com.pdparty.pd.party.tickets.api.server.middleware.authenticationMiddleware
import com.pdparty.pd.party.tickets.service.service.UserService
import com.pdparty.pd.party.tickets.service.service.security.AuthenticationService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.user(userService: UserService, authenticationService: AuthenticationService) {
  route("user") {
    authenticationMiddleware(authenticationService)

    get {
      call.respond(userService.findAll())
    }
  }
}