package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket

class TicketService(val ticketDAO: TicketDAO) {
  suspend fun insertTicket(ticket: Ticket) {
    require(this.find(ticket.id) == null) { "A ticket with that ID already exists." }

    ticketDAO.insert(ticket)
  }

  suspend fun updateTicket(ticket: Ticket) {
    require(this.find(ticket.id) != null) { "Can't update a non existing ticket." }

    ticketDAO.update(ticket)
  }

  suspend fun deleteTicket(ticketId: String) {
    require(this.find(ticketId) != null) { "Can't delete a non existing ticket." }

    ticketDAO.delete(ticketId)
  }

  suspend fun find(ticketId: String): Ticket? =
    ticketDAO.find(ticketId)

  suspend fun findAll(): List<Ticket> =
    ticketDAO.findAll()
}