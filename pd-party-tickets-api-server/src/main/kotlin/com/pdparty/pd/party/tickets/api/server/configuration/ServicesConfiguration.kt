package com.pdparty.pd.party.tickets.api.server.configuration

import com.pdparty.pd.party.tickets.service.`in`.memory.InMemoryTicketDAO
import com.pdparty.pd.party.tickets.service.`in`.memory.InMemoryUserDAO
import com.pdparty.pd.party.tickets.service.model.User
import com.pdparty.pd.party.tickets.service.service.TicketService
import com.pdparty.pd.party.tickets.service.service.UserService
import com.pdparty.pd.party.tickets.service.service.security.AuthenticationService
import com.pdparty.pd.party.tickets.service.service.security.SecurityContext
import com.pdparty.pd.party.tickets.service.service.security.SignupRequest
import com.typesafe.config.ConfigFactory
import kotlinx.coroutines.runBlocking

object ServicesConfiguration {
  private val ticketDAO = InMemoryTicketDAO()
  val ticketService = TicketService(ticketDAO)

  private val userDAO = InMemoryUserDAO()
  val userService = UserService(userDAO)

  val authenticationService = AuthenticationService(userService)

  init {
    runBlocking {
      val adminConfig = ConfigFactory.load().getConfig("admin")

      authenticationService.signup(
        SignupRequest(
          adminConfig.getString("username"),
          adminConfig.getString("password")
        ),
        SecurityContext(User("root", "", ""))
      )
    }
  }
}