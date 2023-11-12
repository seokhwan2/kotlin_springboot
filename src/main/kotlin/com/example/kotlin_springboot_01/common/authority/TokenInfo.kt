package com.example.kotlin_springboot_01.common.authority

data class TokenInfo (
    val grantType: String,
    val accessToken: String,
)