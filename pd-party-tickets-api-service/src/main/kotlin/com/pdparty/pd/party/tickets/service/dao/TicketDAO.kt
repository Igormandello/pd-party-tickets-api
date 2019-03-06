package com.pdparty.pd.party.tickets.service.dao

import com.pdparty.pd.party.tickets.service.model.Ticket

interface TicketDAO {
    fun insert(ticket: Ticket)

    fun update(ticket: Ticket)

    fun delete(ticketId: String)

    fun find(ticketId: String): Ticket?

    fun findAll(): List<Ticket>
}