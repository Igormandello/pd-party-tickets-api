package com.pdparty.pd.party.tickets.service.`in`.memory.dao

import com.pdparty.pd.party.tickets.service.`in`.memory.InMemoryTestCommons.ticketDAO
import com.pdparty.pd.party.tickets.service.dao.TicketDAOTest

class InMemoryTicketDAOTest : TicketDAOTest(ticketDAO, {
  beforeGroup {
    //delete all tickets from database
  }
})