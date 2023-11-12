package com.example.kotlin_springboot_01.common.dto

import com.example.kotlin_springboot_01.common.status.ResultCode

class BaseResponse <T>(
    val resultCode : String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val message: String = ResultCode.SUCCESS.msg,
)