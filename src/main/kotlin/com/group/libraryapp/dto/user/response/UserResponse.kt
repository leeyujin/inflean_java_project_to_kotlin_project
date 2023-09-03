package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

data class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?,
) {

    companion object{
        fun of(user:User): UserResponse{
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age
            )
        }
    }
    // 부생성자보단 위의 정적팩토리 형태가 더 선호됨
/*    constructor(user: User): this(
        id = user.id!!,
        name = user.name,
        age = user.age
    )*/
}