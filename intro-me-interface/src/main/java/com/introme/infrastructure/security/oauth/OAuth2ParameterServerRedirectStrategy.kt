package com.introme.infrastructure.security.oauth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.stereotype.Component

@Component
class OAuth2ParameterServerRedirectStrategy(
    private val delegate: RedirectStrategy = DefaultRedirectStrategy(),
) : RedirectStrategy {
    override fun sendRedirect(request: HttpServletRequest, response: HttpServletResponse, url: String) {
        delegate.sendRedirect(request, response, url)
    }
}