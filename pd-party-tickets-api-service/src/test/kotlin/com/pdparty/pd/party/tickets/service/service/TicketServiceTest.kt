package com.pdparty.pd.party.tickets.service.service

import com.nhaarman.mockitokotlin2.*
import com.pdparty.pd.party.tickets.service.TestCommons
import com.pdparty.pd.party.tickets.service.TestCommons.securityContext
import com.pdparty.pd.party.tickets.service.TestCommons.ticket
import com.pdparty.pd.party.tickets.service.dao.TicketDAO
import com.pdparty.pd.party.tickets.service.model.Ticket
import com.winterbe.expekt.should
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
        ticketService.insertTicket(ticket, securityContext)
        verify(mockTicketDAO).insert(any())
      }
    }

    it("should not duplicate the Ticket") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          whenever(mockTicketDAO.find(eq(ticket.id))).thenReturn(ticket)
          ticketService.insertTicket(ticket, securityContext)
        }
      }
    }

    it("should create an anonymous ticket") {
      runBlocking {
        whenever(mockTicketDAO.findAll()).thenReturn(emptyList())
        val anonymousTicket = ticketService.insertAnonymousTicket(securityContext)

        anonymousTicket.should.satisfy {
          it.id == "anonymous-000" && it.anonymous
        }
        verify(mockTicketDAO).insert(any())
      }
    }

    it("should create a second anonymous ticket") {
      runBlocking {
        whenever(mockTicketDAO.findAll()).thenReturn(listOf(ticket.copy(id = "anonymous-000", anonymous = true)))
        val anonymousTicket = ticketService.insertAnonymousTicket(securityContext)

        anonymousTicket.should.satisfy {
          it.id == "anonymous-001" && it.anonymous
        }
        verify(mockTicketDAO).insert(any())
      }
    }

    it("should update the Ticket") {
      runBlocking {
        whenever(mockTicketDAO.find(eq(ticket.id))).thenReturn(ticket)
        ticketService.updateTicket(ticket, securityContext)
        verify(mockTicketDAO).update(any())
      }
    }

    it("should not update a non existing Ticket") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          ticketService.updateTicket(ticket, securityContext)
        }
      }
    }

    it("should delete the Ticket") {
      runBlocking {
        whenever(mockTicketDAO.find(eq(ticket.id))).thenReturn(ticket)
        ticketService.deleteTicket(ticket.id, securityContext)
        verify(mockTicketDAO).delete(any())
      }
    }

    it("should not delete a non existing Ticket") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          ticketService.deleteTicket(ticket.id, securityContext)
        }
      }
    }
  }
})