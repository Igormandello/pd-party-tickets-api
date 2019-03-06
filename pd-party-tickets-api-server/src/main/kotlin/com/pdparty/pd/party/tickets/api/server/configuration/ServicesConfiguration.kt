package com.pdparty.pd.party.tickets.api.server.configuration

import com.pdparty.pd.party.tickets.service.`in`.memory.InMemoryTicketDAO
import com.pdparty.pd.party.tickets.service.service.TicketService

object ServicesConfiguration {
  private val ticketDao = InMemoryTicketDAO()
  val ticketService = TicketService(ticketDao)
}