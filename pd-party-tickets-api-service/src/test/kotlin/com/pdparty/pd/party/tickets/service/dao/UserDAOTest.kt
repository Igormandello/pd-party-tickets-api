package com.pdparty.pd.party.tickets.service.dao

import com.pdparty.pd.party.tickets.service.TestCommons
import com.pdparty.pd.party.tickets.service.TestCommons.user
import com.winterbe.expekt.should
import kotlinx.coroutines.runBlocking
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

abstract class UserDAOTest(userDAO: UserDAO, spec: Spec.() -> Unit) : Spek({
  spec()

  describe("an User DAO") {
    it("should insert the user") {
      runBlocking {
        userDAO.insert(user)

        userDAO.find(user.username).should.not.be.`null`
      }
    }

    it("should return all users") {
      runBlocking {
        userDAO.insert(user.copy(username = "Another Username"))

        userDAO.findAll().size.should.be.equal(2)
      }
    }

    it("should delete the user") {
      runBlocking {
        userDAO.delete(user.username)

        userDAO.find(user.username).should.be.`null`
      }
    }
  }
})