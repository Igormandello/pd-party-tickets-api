package com.pdparty.pd.party.tickets.service.dao

import com.pdparty.pd.party.tickets.service.model.Ticket

interface TicketDAO {
    suspend fun insert(ticket: Ticket)

    suspend fun update(ticket: Ticket)

    suspend fun delete(ticketId: String)

    suspend fun find(ticketId: String): Ticket?

    suspend fun findAll(): List<Ticket>
}