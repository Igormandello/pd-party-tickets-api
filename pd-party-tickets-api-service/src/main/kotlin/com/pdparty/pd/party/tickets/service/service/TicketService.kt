package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket

class TicketService(val ticketDao: TicketDAO) {
  suspend fun insertTicket(ticket: Ticket) {
    require(this.find(ticket.id) == null) { "A ticket with that ID already exists." }

    ticketDao.insert(ticket)
  }

  suspend fun updateTicket(ticket: Ticket) {
    require(this.find(ticket.id) != null) { "Can't update a non existing ticket." }

    ticketDao.update(ticket)
  }

  suspend fun deleteTicket(ticketId: String) {
    require(this.find(ticketId) != null) { "Can't delete a non existing ticket." }

    ticketDao.delete(ticketId)
  }

  suspend fun find(ticketId: String): Ticket? =
    ticketDao.find(ticketId)

  suspend fun findAll(): List<Ticket> =
    ticketDao.findAll()
}