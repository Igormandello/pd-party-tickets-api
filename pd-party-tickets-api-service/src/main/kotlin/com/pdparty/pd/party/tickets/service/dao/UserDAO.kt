package com.pdparty.pd.party.tickets.service.dao

import com.pdparty.pd.party.tickets.service.model.User

interface UserDAO {
  suspend fun insert(user: User)

  suspend fun delete(username: String)

  suspend fun find(username: String): User?

  suspend fun findAll(): List<User>
}