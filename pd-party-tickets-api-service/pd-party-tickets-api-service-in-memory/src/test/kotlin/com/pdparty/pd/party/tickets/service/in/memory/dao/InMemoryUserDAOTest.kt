package com.pdparty.pd.party.tickets.service.`in`.memory.dao

import com.pdparty.pd.party.tickets.service.`in`.memory.InMemoryTestCommons.userDAO
import com.pdparty.pd.party.tickets.service.dao.UserDAOTest
import kotlinx.coroutines.runBlocking

class InMemoryUserDAOTest : UserDAOTest(userDAO, {
  beforeGroup {
    runBlocking {
      userDAO.findAll().forEach {
        userDAO.delete(it.username)
      }
    }
  }
})