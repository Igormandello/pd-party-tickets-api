package com.pdparty.pd.party.tickets.api.server.api.v1.user

import com.pdparty.pd.party.tickets.service.service.UserService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.user(userService: UserService) {
  route("user") {
    get {
      call.respond(userService.findAll())
    }
  }
}