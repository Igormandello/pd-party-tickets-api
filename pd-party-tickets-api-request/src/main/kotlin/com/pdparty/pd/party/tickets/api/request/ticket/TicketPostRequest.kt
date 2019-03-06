package com.pdparty.pd.party.tickets.api.request.ticket

import com.pdparty.pd.party.tickets.service.model.Ticket
import java.time.Instant

data class TicketPostRequest(val id: String) {
  fun toTicket(): Ticket =
      Ticket(
        id = id,
        date = Instant.now()
      )
}