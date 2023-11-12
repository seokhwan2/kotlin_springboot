package com.example.kotlin_springboot_01.common.exception

// RuntimeException상속받고있어서 파라미터로 넘어가는 메시지는 RuntimeException에 그대로 전달하게됨
class InvalidInputException (
    val fieldName : String = "",
    message: String = "Invalid Input"
) : RuntimeException(message)
