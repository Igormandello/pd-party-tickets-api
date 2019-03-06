package com.pdparty.pd.party.tickets.api.request.ticket

import com.pdparty.pd.party.tickets.service.model.Ticket
import java.time.Instant

data class TicketPutRequest(val date: Instant) {
  fun toTicket(id: String): Ticket =
      Ticket(
        id = id,
        date = date
      )
}