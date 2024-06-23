package com.introme.interfaces.user

import com.introme.infrastructure.security.jwt.JwtService
import com.introme.user.service.UserQuery
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    private val userQuery: UserQuery,
    private val jwtService: JwtService,
) {

    @GetMapping(
        value = ["/api/user"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getIntroMeUserByToken(
        @RequestParam(name = "token") token: String,
    ): IntroMeUserDto {
        val extractUserId = jwtService.extractUserId(token)
        return userQuery.getUser(extractUserId).toDto()
    }
}