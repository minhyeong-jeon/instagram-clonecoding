package com.example.instagram.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.config.auth.PrincipalDetails;
import com.example.instagram.web.dto.CMRespDto;
import com.example.instagram.web.service.FollowService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FollowController {
	
	private final FollowService followService;
	
	@PostMapping("/follow/{toUserId}") // /follow/3
	public CMRespDto<?> follow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId) {
		int result = followService.팔로우(principalDetails.getUser().getId(), toUserId);
		return new CMRespDto<>(1,result);
	}
	
	@DeleteMapping("/follow/{toUserId}") // /follow/3
	public CMRespDto<?> unFollow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId) {
		int result = followService.언팔로우(principalDetails.getUser().getId(), toUserId);
		return new CMRespDto<>(1,result);
	}
}