package com.ygo.ygodeckbuilder.service;

import com.ygo.ygodeckbuilder.controller.dto.MemberRegistrationDto;
import com.ygo.ygodeckbuilder.domain.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Member save(MemberRegistrationDto registrationDto);
}