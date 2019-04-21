package com.pdparty.pd.party.tickets.api.server

fun main() {
  val port = System.getenv("PORT")?.toInt() ?: 8080
  val server = Server(port)

  println("Server starting")
  server.start()
}