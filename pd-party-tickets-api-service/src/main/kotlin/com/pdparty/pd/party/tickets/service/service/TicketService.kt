package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket

class TicketService(val ticketDao: TicketDAO) {
  suspend fun insertTicket(ticket: Ticket) {}

  suspend fun updateTicket(ticket: Ticket) {}

  suspend fun deleteTicket(ticketId: String) {}

  suspend fun find(ticketId: String): Ticket? =
    ticketDao.find(ticketId)

  suspend fun findAll(): List<Ticket> =
    ticketDao.findAll()
}