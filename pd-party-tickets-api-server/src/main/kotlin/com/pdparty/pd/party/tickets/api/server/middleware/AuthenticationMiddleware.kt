package com.pdparty.pd.party.tickets.api.server.middleware

import com.pdparty.pd.party.tickets.api.server.configuration.AUTHENTICATION_ATTRIBUTE
import com.pdparty.pd.party.tickets.api.server.configuration.getSecurityContext
import com.pdparty.pd.party.tickets.service.service.security.AuthenticationService
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.request.header
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.authenticationMiddleware(authenticationService: AuthenticationService) {
  intercept(ApplicationCallPipeline.Features) {
    call.request.header(HttpHeaders.Authorization)
      ?.let { getSecurityContext(authenticationService, it) }
      ?.also { call.attributes.put(AUTHENTICATION_ATTRIBUTE, it) }
      ?: call.respond(HttpStatusCode.Forbidden)
  }
}