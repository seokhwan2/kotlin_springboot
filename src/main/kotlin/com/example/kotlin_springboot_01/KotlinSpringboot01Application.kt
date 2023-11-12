package com.example.kotlin_springboot_01

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringboot01Application

fun main(args: Array<String>) {
	runApplication<KotlinSpringboot01Application>(*args)
}
