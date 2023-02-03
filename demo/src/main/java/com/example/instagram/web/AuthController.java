package com.example.instagram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.instagram.service.AuthService;
import com.example.instagram.utils.Script;
import com.example.instagram.web.auth.dto.UserJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm(){

	return "auth/joinForm";
	}
	
	//회원가입
	@PostMapping("/auth/join")
	public @ResponseBody String join(UserJoinReqDto userJoinReqDto) {
		authService.회원가입(userJoinReqDto.toEntity());
		return Script.href("성공","/auth/loginForm");
	
	}

}
