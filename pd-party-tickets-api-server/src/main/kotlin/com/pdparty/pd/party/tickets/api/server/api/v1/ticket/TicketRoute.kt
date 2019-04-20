package com.pdparty.pd.party.tickets.api.server.api.v1.ticket

import com.pdparty.pd.party.tickets.api.request.ticket.TicketPostRequest
import com.pdparty.pd.party.tickets.api.request.ticket.TicketPutRequest
import com.pdparty.pd.party.tickets.api.server.configuration.securityContext
import com.pdparty.pd.party.tickets.api.server.middleware.authenticationMiddleware
import com.pdparty.pd.party.tickets.service.service.TicketService
import com.pdparty.pd.party.tickets.service.service.security.AuthenticationService
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.pipeline.PipelineContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun Route.ticket(ticketService: TicketService, authenticationService: AuthenticationService) {
  route("ticket") {
    authenticationMiddleware(authenticationService)

    route("{id}") {
      fun PipelineContext<Unit, ApplicationCall>.getId(): String = call.parameters["id"]!!

      get {
        val ticket = ticketService.find(this.getId(), this.securityContext())
        if (ticket == null)
          call.respond(HttpStatusCode.NotFound)
        else
          call.respond(ticket)
      }

      put {
        val putRequest = call.receive<TicketPutRequest>()
        ticketService.updateTicket(putRequest.toTicket(this.getId()), this.securityContext())

        call.respond(HttpStatusCode.NoContent)
      }

      delete {
        ticketService.deleteTicket(this.getId(), this.securityContext())

        call.respond(HttpStatusCode.NoContent)
      }
    }

    get {
      val wait = call.request.queryParameters["wait"]

      if (wait == null) {
        call.respond(ticketService.findAll())
      } else {
        val currentHash = ticketService.findAll().hashCode()
        while (ticketService.findAll().hashCode() == currentHash) {
          delay(1000)
        }

        call.respond( ticketService.findAll())
      }
    }

    post {
      val postRequest = call.receive<TicketPostRequest>()
      ticketService.insertTicket(postRequest.toTicket())

      call.respond(HttpStatusCode.Created)
    }
  }
}