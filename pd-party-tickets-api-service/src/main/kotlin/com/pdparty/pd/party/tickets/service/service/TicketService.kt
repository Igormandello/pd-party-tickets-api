package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket
import com.pdparty.pd.party.tickets.service.service.security.SecurityContext
import org.slf4j.LoggerFactory

class TicketService(val ticketDAO: TicketDAO) {
  suspend fun insertTicket(ticket: Ticket, securityContext: SecurityContext) {
    logger.debug("User {} inserting ticket {}", securityContext.user.username, ticket.id)
    require(this.find(ticket.id) == null) { "A ticket with that ID already exists." }

    ticketDAO.insert(ticket)
  }

  suspend fun updateTicket(ticket: Ticket, securityContext: SecurityContext) {
    logger.debug("User {} updating ticket {}", securityContext.user.username, ticket.id)
    require(this.find(ticket.id) != null) { "Can't update a non existing ticket." }

    ticketDAO.update(ticket)
  }

  suspend fun deleteTicket(ticketId: String, securityContext: SecurityContext) {
    logger.debug("User {} deleting ticket {}", securityContext.user.username, ticketId)
    require(this.find(ticketId) != null) { "Can't delete a non existing ticket." }

    ticketDAO.delete(ticketId)
  }

  suspend fun find(ticketId: String): Ticket? =
    ticketDAO.find(ticketId)

  suspend fun findAll(): List<Ticket> =
    ticketDAO.findAll()

  companion object {
    private val logger = LoggerFactory.getLogger(TicketService::class.qualifiedName)
  }
}