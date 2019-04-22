package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket
import com.pdparty.pd.party.tickets.service.service.security.SecurityContext
import org.slf4j.LoggerFactory
import java.time.Instant

class TicketService(val ticketDAO: TicketDAO) {
  suspend fun insertTicket(ticket: Ticket, securityContext: SecurityContext): Ticket {
    logger.debug("User {} inserting ticket {}", securityContext.user.username, ticket.id)
    require(this.find(ticket.id) == null) { "A ticket with that ID already exists." }

    ticketDAO.insert(ticket)
    return ticket
  }

  suspend fun insertAnonymousTicket(securityContext: SecurityContext): Ticket {
    logger.debug("User {} inserting anonymous ticket", securityContext.user.username)

    val lastAnonymousTicketId =
        ticketDAO.findAll()
          .filter { it.anonymous }
          .map { it.id.split("-").last().toInt() }
          .sorted()
          .lastOrNull()

    val ticket = Ticket(
      id = "anonymous-${lastAnonymousTicketId?.let { it + 1 }?.toString()?.padStart(3, '0') ?: "000"}",
      date = Instant.now(),
      anonymous = true
    )

    ticketDAO.insert(ticket)
    return ticket
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