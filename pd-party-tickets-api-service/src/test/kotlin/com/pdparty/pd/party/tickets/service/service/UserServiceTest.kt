package com.pdparty.pd.party.tickets.service.service

import com.nhaarman.mockitokotlin2.*
import com.pdparty.pd.party.tickets.service.TestCommons.user
import com.pdparty.pd.party.tickets.service.dao.UserDAO
import kotlinx.coroutines.runBlocking
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertFailsWith

class UserServiceTest : Spek({
  val mockUserDAO = mock<UserDAO>()
  val userService = UserService(mockUserDAO)

  beforeEachTest {
    reset(mockUserDAO)
  }

  describe("an User Service") {
    it("should create a user") {
      runBlocking {
        userService.insertUser(user)
        verify(mockUserDAO).insert(any())
      }
    }

    it("should not duplicate the user") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          whenever(mockUserDAO.find(eq(user.username))).thenReturn(user)
          userService.insertUser(user)
        }
      }
    }

    it("should delete the user") {
      runBlocking {
        whenever(mockUserDAO.find(eq(user.username))).thenReturn(user)
        userService.deleteUser(user.username)
        verify(mockUserDAO).delete(any())
      }
    }

    it("should not delete a non existing user") {
      assertFailsWith<IllegalArgumentException> {
        runBlocking {
          userService.deleteUser(user.username)
        }
      }
    }
  }
})