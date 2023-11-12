package com.example.kotlin_springboot_01.member.service


import com.example.kotlin_springboot_01.common.authority.TokenInfo
import com.example.kotlin_springboot_01.common.exception.InvalidInputException
import com.example.kotlin_springboot_01.common.status.ROLE
import com.example.kotlin_springboot_01.member.dto.LoginDto
import com.example.kotlin_springboot_01.member.dto.MemberDtoResponse
import com.example.kotlin_springboot_01.member.dto.MemberDtos
import com.example.kotlin_springboot_01.member.entity.Member
import com.example.kotlin_springboot_01.member.repository.MemberRepository
import com.example.kotlin_springboot_01.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository

//    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
//    private val jwtTokenProvider: JwtTokenProvider,

    ) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtos): String {
        var member: Member? = memberRepository
            .findByLoginId(memberDtoRequest.loginId)
        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }
        // 사용자 정보 저장
        member = memberDtoRequest.toEntity()
        memberRepository.save(member)
        // 권한 저장
        val memberRole = Member.MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)
        return "회원가입이 완료되었습니다."
    }

    /*
    * 로그인 -> 토큰 발행
    * */
    fun login(loginDto: LoginDto): TokenInfo {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication =
            authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }

    /*
    * 내 정보 조회
    * */
    fun serarchMyInfo(id: Long): MemberDtoResponse {
        val member: Member = memberRepository.findByIdOrNull(id) ?: throw InvalidInputException("id", "회원번호(${id}가 존재하지 않는 유저입니다.")
        return member.toDto()
    }
}