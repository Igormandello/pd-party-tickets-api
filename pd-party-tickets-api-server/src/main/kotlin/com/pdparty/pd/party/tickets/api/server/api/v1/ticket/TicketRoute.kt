package com.pdparty.pd.party.tickets.api.server.api.v1.ticket

import com.pdparty.pd.party.tickets.api.request.ticket.TicketPostRequest
import com.pdparty.pd.party.tickets.api.request.ticket.TicketPutRequest
import com.pdparty.pd.party.tickets.service.service.TicketService
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.util.pipeline.PipelineContext

fun Route.ticketRoute(ticketService: TicketService) {
  route("ticket") {
    route("{id}") {
      fun PipelineContext<Unit, ApplicationCall>.getId(): String = call.parameters["id"]!!

      get {
        val ticket = ticketService.find(this.getId())
        if (ticket == null)
          call.respond(HttpStatusCode.NotFound)
        else
          call.respond(ticket)
      }

      put {
        val putRequest = call.receive<TicketPutRequest>()
        ticketService.updateTicket(putRequest.toTicket(this.getId()))

        call.respond(HttpStatusCode.NoContent)
      }

      delete {
        ticketService.deleteTicket(this.getId())

        call.respond(HttpStatusCode.NoContent)
      }
    }

    get {
      call.respond(ticketService.findAll())
    }

    post {
      val postRequest = call.receive<TicketPostRequest>()
      ticketService.insertTicket(postRequest.toTicket())

      call.respond(HttpStatusCode.Created)
    }
  }
}