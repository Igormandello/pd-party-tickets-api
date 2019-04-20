package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.UserDAO
import com.pdparty.pd.party.tickets.service.model.User
import com.pdparty.pd.party.tickets.service.service.security.SecurityContext
import org.slf4j.LoggerFactory

class UserService(val userDAO: UserDAO) {
  suspend fun insertUser(user: User, securityContext: SecurityContext) {
    logger.debug("User {} inserting user {}", securityContext.user.username, user.username)
    require(this.find(user.username) == null) { "A user with that username already exists." }

    userDAO.insert(user)
  }

  suspend fun deleteUser(username: String, securityContext: SecurityContext) {
    logger.debug("User {} deleting user {}", securityContext.user.username, username)
    require(this.find(username) != null) { "Can't delete a non existing user." }

    userDAO.delete(username)
  }

  suspend fun find(username: String): User? =
    userDAO.find(username)

  suspend fun findAll(): List<User> =
    userDAO.findAll()

  companion object {
    private val logger = LoggerFactory.getLogger(TicketService::class.qualifiedName)
  }
}