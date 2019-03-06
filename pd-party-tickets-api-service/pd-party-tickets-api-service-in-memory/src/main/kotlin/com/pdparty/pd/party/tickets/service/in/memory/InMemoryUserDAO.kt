package com.pdparty.pd.party.tickets.service.`in`.memory

import com.pdparty.pd.party.tickets.service.dao.UserDAO
import com.pdparty.pd.party.tickets.service.model.User

class InMemoryUserDAO : UserDAO {
  private val users = mutableListOf<User>()

  override suspend fun insert(user: User) {
    if (!this.users.any { it.username == user.username }) {
      users.add(user)
    }
  }

  override suspend fun delete(username: String) {
    val index = this.users.indexOfFirst { it.username == username }
    if (index >= 0) {
      this.users.removeAt(index)
    }
  }

  override suspend fun find(username: String): User? =
    this.users.firstOrNull { it.username == username }

  override suspend fun findAll(): List<User> =
    this.users.toList()
}