package com.introme.interfaces.user

import com.introme.user.entity.User



data class IntroMeUserDto(
    val id: Long,
    val email: String,
    val name: String,
    val picture: String?,
)


fun User.toDto() = IntroMeUserDto(
    id = this.id,
    email = this.email,
    name = this.name,
    picture = this.picture
)