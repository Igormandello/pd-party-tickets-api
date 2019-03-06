package com.pdparty.pd.party.tickets.service

import com.pdparty.pd.party.tickets.service.model.Ticket
import java.time.Instant
import java.util.Random

object TestCommons {
  val ticket = Ticket(
    id = "0042",
    date = Instant.now()
  )
}