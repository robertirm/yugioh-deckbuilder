package com.ygo.ygodeckbuilder.controller;

import com.ygo.ygodeckbuilder.controller.dto.MemberRegistrationDto;
import com.ygo.ygodeckbuilder.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class MemberRegistrationController {
    private MemberService memberService;

    public MemberRegistrationController(MemberService memberService){
        super();
        this.memberService = memberService;
    }

    @ModelAttribute("user")
    public MemberRegistrationDto userRegistrationDto() {
        return new MemberRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") MemberRegistrationDto registrationDto) {
        memberService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
