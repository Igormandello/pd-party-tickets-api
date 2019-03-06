package com.pdparty.pd.party.tickets.service.`in`.memory

import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket

class InMemoryTicketDAO : TicketDAO {
  override suspend fun insert(ticket: Ticket) {
    TODO("not implemented")
  }

  override suspend fun update(ticket: Ticket) {
    TODO("not implemented")
  }

  override suspend fun delete(ticketId: String) {
    TODO("not implemented")
  }

  override suspend fun find(ticketId: String): Ticket? {
    TODO("not implemented")
  }

  override suspend fun findAll(): List<Ticket> {
    TODO("not implemented")
  }
}