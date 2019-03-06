package com.pdparty.pd.party.tickets.api.server.api.v1

import com.pdparty.pd.party.tickets.api.server.api.v1.auth.auth
import com.pdparty.pd.party.tickets.api.server.api.v1.ticket.ticket
import com.pdparty.pd.party.tickets.api.server.api.v1.user.user
import com.pdparty.pd.party.tickets.api.server.configuration.ServicesConfiguration.authenticationService
import com.pdparty.pd.party.tickets.api.server.configuration.ServicesConfiguration.ticketService
import com.pdparty.pd.party.tickets.api.server.configuration.ServicesConfiguration.userService
import io.ktor.routing.Route
import io.ktor.routing.route

fun Route.v1() {
  route("v1") {
    ticket(ticketService, authenticationService)
    user(userService, authenticationService)
    auth(authenticationService)
  }
}