package com.pdparty.pd.party.tickets.service.service

import com.nhaarman.mockitokotlin2.*
import com.pdparty.pd.party.tickets.service.TestCommons.ticket
import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import kotlinx.coroutines.runBlocking
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertFailsWith

class TicketServiceTest : Spek({
  val mockTicketDAO = mock<TicketDAO>()
  val ticketService = TicketService(mockTicketDAO)

  beforeEachTest {
    reset(mockTicketDAO)
  }

  describe("a Ticket Service") {
    it("should create a Ticket") {
      runBlocking {
        ticketService.insertTicket(ticket)
        verify(mockTicketDAO).insert(any())
      }
    }

    it("should not duplicate the Ticket") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          whenever(mockTicketDAO.find(eq(ticket.id))).thenReturn(ticket)
          ticketService.insertTicket(ticket)
        }
      }
    }

    it("should update the Ticket") {
      runBlocking {
        whenever(mockTicketDAO.find(eq(ticket.id))).thenReturn(ticket)
        ticketService.updateTicket(ticket)
        verify(mockTicketDAO).update(any())
      }
    }

    it("should not update a non existing Ticket") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          ticketService.updateTicket(ticket)
        }
      }
    }

    it("should delete the Ticket") {
      runBlocking {
        whenever(mockTicketDAO.find(eq(ticket.id))).thenReturn(ticket)
        ticketService.deleteTicket(ticket.id)
        verify(mockTicketDAO).delete(any())
      }
    }

    it("should not delete a non existing Ticket") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          ticketService.deleteTicket(ticket.id)
        }
      }
    }
  }
})