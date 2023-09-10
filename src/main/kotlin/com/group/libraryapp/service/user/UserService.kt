package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun saveUser(request: UserCreateRequest){
        val newUser = User(request.name, request.age)
        userRepository.save(newUser)
    }

    @Transactional(readOnly=true)
    fun getUsers(): List<UserResponse>{
        return userRepository.findAll()
                // it로 단축한방식
//            .map { UserResponse(it) }
                // 생성자 호출방식
//            .map( ::UserResponse )
            .map { user -> UserResponse.of(user) }
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest){
//        val user = userRepository.findById(request.id).orElseThrow ( ::IllegalArgumentException ) // 생성자 호출
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String){
//        val user = userRepository.findByName(name).orElseThrow (:: IllegalArgumentException )
        val user = userRepository.findByName(name) ?: fail()
        userRepository.delete(user)
    }

    @Transactional(readOnly = true)
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        // 메소드 레퍼런스
        return userRepository.findAllWithHistories().map(UserLoanHistoryResponse::of)

    }
}

