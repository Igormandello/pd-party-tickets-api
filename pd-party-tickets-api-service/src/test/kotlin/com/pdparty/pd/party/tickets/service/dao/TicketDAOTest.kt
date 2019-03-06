package com.pdparty.pd.party.tickets.service.dao

import com.pdparty.pd.party.tickets.service.TestCommons.ticket
import com.winterbe.expekt.should
import kotlinx.coroutines.runBlocking
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

abstract class TicketDAOTest(ticketDAO: TicketDAO, spec: Spec.() -> Unit) : Spek({
  spec()

  describe("a  Ticket DAO") {
    it("should insert the Ticket") {
      runBlocking {
        ticketDAO.insert(ticket)

        ticketDAO.find(ticket.id).should.not.be.`null`
      }
    }

    it("should update the Ticket") {
      runBlocking {
        val newInstant = ticket.date.plusSeconds(10)
        val newTicket = ticket.copy(
          date = newInstant
        )

        ticketDAO.update(newTicket)
        ticketDAO.find(ticket.id)!!.date.should.be.equal(newInstant)
      }
    }

    it("should return all tickets") {
      runBlocking {
        ticketDAO.insert(ticket.copy(id = "0043"))

        ticketDAO.findAll().size.should.be.equal(2)
      }
    }

    it("should delete the ticket") {
      runBlocking {
        ticketDAO.delete(ticket.id)

        ticketDAO.find(ticket.id).should.be.`null`
      }
    }
  }
})