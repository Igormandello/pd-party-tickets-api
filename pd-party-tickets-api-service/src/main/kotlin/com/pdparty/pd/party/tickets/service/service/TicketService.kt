package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket

class TicketService(val ticketDao: TicketDAO) {
  fun insertTicket(ticket: Ticket) {}

  fun updateTicket(ticket: Ticket) {}

  fun deleteTicket(ticketId: String) {}

  fun find(ticketId: String): Ticket? =
    ticketDao.find(ticketId)

  fun findAll(): List<Ticket> =
    ticketDao.findAll()
}