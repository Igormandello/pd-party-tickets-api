package com.pdparty.pd.party.tickets.service

import com.pdparty.pd.party.tickets.service.model.Ticket
import com.pdparty.pd.party.tickets.service.model.User
import com.pdparty.pd.party.tickets.service.service.security.SecurityContext
import java.time.Instant
import java.util.Random

object TestCommons {
  val ticket = Ticket(
    id = "0042",
    date = Instant.now(),
    anonymous = false
  )

  val user = User(
    username = "Test user",
    password = "testpasswordsalted",
    salt = "salt123"
  )

  val securityContext = SecurityContext(user)
}