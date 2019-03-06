package com.pdparty.pd.party.tickets.service.`in`.memory.dao

import com.pdparty.pd.party.tickets.service.`in`.memory.InMemoryTestCommons.ticketDAO
import com.pdparty.pd.party.tickets.service.dao.TicketDAOTest
import kotlinx.coroutines.runBlocking

class InMemoryTicketDAOTest : TicketDAOTest(ticketDAO, {
  beforeGroup {
    runBlocking {
      ticketDAO.findAll().forEach {
        ticketDAO.delete(it.id)
      }
    }
  }
})