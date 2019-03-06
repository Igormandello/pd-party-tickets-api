package com.pdparty.pd.party.tickets.api.server.api.v1

import com.pdparty.pd.party.tickets.api.server.api.v1.ticket.ticketRoute
import com.pdparty.pd.party.tickets.api.server.configuration.ServicesConfiguration.ticketService
import io.ktor.routing.Route
import io.ktor.routing.route

fun Route.v1() {
  route("v1") {
    ticketRoute(ticketService)
  }
}