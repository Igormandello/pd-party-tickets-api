package com.pdparty.pd.party.tickets.service.service

import com.pdparty.pd.party.tickets.service.dao.UserDAO
import com.pdparty.pd.party.tickets.service.model.User

class UserService(val userDAO: UserDAO) {
  suspend fun insertUser(user: User) {
    require(this.find(user.username) == null) { "A user with that username already exists." }

    userDAO.insert(user)
  }

  suspend fun deleteUser(username: String) {
    require(this.find(username) != null) { "Can't delete a non existing user." }

    userDAO.delete(username)
  }

  suspend fun find(username: String): User? =
    userDAO.find(username)

  suspend fun findAll(): List<User> =
    userDAO.findAll()
}