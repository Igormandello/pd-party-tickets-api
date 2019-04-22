package com.pdparty.pd.party.tickets.service.`in`.memory

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket

class InMemoryTicketDAO : TicketDAO {
  private val tickets = mutableListOf<Ticket>()

  override suspend fun insert(newTicket: Ticket): Ticket {
    if (this.tickets.any { it.id == newTicket.id }) {
      throw IllegalArgumentException("Duplicate ID")
    }

    tickets.add(newTicket)
    return newTicket
  }

  override suspend fun update(ticket: Ticket) {
    val index = this.tickets.indexOfFirst { it.id == ticket.id }
    if (index >= 0) {
      this.tickets[index] = ticket
    }
  }

  override suspend fun delete(ticketId: String) {
    val index = this.tickets.indexOfFirst { it.id == ticketId }
    if (index >= 0) {
      this.tickets.removeAt(index)
    }
  }

  override suspend fun find(ticketId: String): Ticket? =
    this.tickets.firstOrNull { it.id == ticketId }

  override suspend fun findAll(): List<Ticket> =
    this.tickets.toList()
}