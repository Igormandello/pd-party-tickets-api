package com.pdparty.pd.party.tickets.api.server.configuration

import com.pdparty.pd.party.tickets.service.service.security.AuthenticationService
import com.pdparty.pd.party.tickets.service.service.security.SecurityContext
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.util.AttributeKey
import io.ktor.util.pipeline.PipelineContext

val AUTHENTICATION_ATTRIBUTE = AttributeKey<SecurityContext>("authentication")

fun PipelineContext<Unit, ApplicationCall>.securityContext(): SecurityContext = call.attributes[AUTHENTICATION_ATTRIBUTE]

suspend fun getSecurityContext(authenticationService: AuthenticationService, header: String): SecurityContext? =
  header.takeIf { it.startsWith("Bearer ") }
    ?.substring(7)
    ?.let { authenticationService.createSecurityContext(it) }