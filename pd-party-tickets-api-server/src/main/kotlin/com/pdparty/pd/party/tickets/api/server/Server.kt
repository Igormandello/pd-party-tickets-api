package com.pdparty.pd.party.tickets.api.server

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class Server(port: Int = 8080) {
  private val server = embeddedServer(Netty, port) {
    install(CallLogging)

    install(CORS.Feature) {
      anyHost()
      method(HttpMethod.Get)
      method(HttpMethod.Post)
      method(HttpMethod.Delete)
      method(HttpMethod.Put)
      header("Authorization")
    }

    install(ContentNegotiation) {
      jackson {
        enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
        disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        registerModule(Jdk8Module())
        registerModule(JavaTimeModule())

        configure(SerializationFeature.INDENT_OUTPUT, true)
        setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
          indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
          indentObjectsWith(DefaultIndenter("  ", "\n"))
        })
      }
    }

    routing {
      get("health") {
        call.respond(HttpStatusCode.OK)
      }
    }
  }

  fun start() {
    this.server.start()
  }
}