package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryAppApplication

fun main(args: Array<String>){
    // 가변인자의 경우 * 추가
    runApplication<LibraryAppApplication>(*args)
}
