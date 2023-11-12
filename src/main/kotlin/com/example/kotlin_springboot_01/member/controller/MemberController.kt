package com.example.kotlin_springboot_01.member.controller

import com.example.kotlin_springboot_01.common.authority.TokenInfo
import com.example.kotlin_springboot_01.common.dto.BaseResponse
import com.example.kotlin_springboot_01.member.dto.LoginDto
import com.example.kotlin_springboot_01.member.dto.MemberDtoResponse
import com.example.kotlin_springboot_01.member.dto.MemberDtos
import com.example.kotlin_springboot_01.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
class MemberController(
    private val memberService: MemberService,

) {
    /*
    * 회원가입
    * */
    @PostMapping("/signup")
    fun signUp(
        @RequestBody @Valid
        memberDtoRequest: MemberDtos,
    ): BaseResponse<Unit> {
        val resultMsg: String = memberService.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }

    /*
    * 로그인
    * */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }

    /*
    * 내 정보 보기
    * */
    @GetMapping("/info/{id}")
    fun searchMyInfo(@PathVariable id: Long): BaseResponse<MemberDtoResponse> {
        val response = memberService.serarchMyInfo(id)
        return BaseResponse(data = response)

    }
}
