package com.example.instagram.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.instagram.config.auth.PrincipalDetails;
import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.image.Image;
import com.example.instagram.service.CommentService;
import com.example.instagram.service.ImageService;
import com.example.instagram.service.LikesService;
import com.example.instagram.web.dto.CMRespDto;
import com.example.instagram.web.dto.image.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;
	private final LikesService likesService;
	private final CommentService commentService;
		
		
//	@GetMapping({"/","/image/feed"})
//	public String feed(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails, 
//			@PageableDefault(size = 3, sort="id", direction= Sort.Direction.DESC) Pageable pageable) { // 스프링프레임워크 데이터 도멘이꺼임
//		
//		// ssar이 누구를 팔로우했는지 정보확인 -> love
//		// ssar로 로그인했으니 -> image1(love), image2(love) 만 들고 피드로 가게
//		
//		model.addAttribute("images",imageService.피드이미지(principalDetails.getUser().getId(),pageable));
//		return "image/feed";
//	}
	
	@GetMapping({"/","/image/feed"})
	public String feed() { 
		return "image/feed";
	}
	
	// 주소: /image?page=0
	@GetMapping("/image")
	public @ResponseBody CMRespDto<?> image(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails, 
			@PageableDefault(size = 3, sort="id", direction= Sort.Direction.DESC) Pageable pageable) { // 스프링프레임워크 데이터 도멘이꺼임
		
		// ssar이 누구를 팔로우했는지 정보확인 -> love
		// ssar로 로그인했으니 -> image1(love), image2(love) 만 들고 피드로 가게
		Page<Image> pages = imageService.피드이미지(principalDetails.getUser().getId(),pageable);
		return new CMRespDto<>(1,pages); // MessageConverter 발동= Jackson 무한잠초가 발생할수있음
	}
	
	@GetMapping("/image/explore")
	public String explore(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

		model.addAttribute("images", imageService.인기사진(principalDetails.getUser().getId()));

		return "image/explore";
	}
	
	@GetMapping("image/upload")
	public String upload() {
		return "image/upload";
	}
	
	@PostMapping("/image")
	public String image(ImageReqDto imageReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		imageService.사진업로드(imageReqDto,principalDetails);
		return "redirect:user/"+principalDetails.getUser().getId();
	}
	
	@PostMapping("/image/{imageId}/likes")
	public @ResponseBody CMRespDto<?> like(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int imageId){
		likesService.좋아요(imageId, principalDetails.getUser().getId());
		return new CMRespDto<>(1, null);
	}
	
	@DeleteMapping("/image/{imageId}/likes")
	public @ResponseBody CMRespDto<?> unLike(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int imageId){
		likesService.싫어요(imageId, principalDetails.getUser().getId());
		return new CMRespDto<>(1, null);
	}
	
	// 댓글로 받을 값 : imageId, content
	@PostMapping("/image/{imageId}/comment")
	public @ResponseBody CMRespDto<?> save(@PathVariable int imageId, @RequestBody String content, @AuthenticationPrincipal PrincipalDetails principalDetails){   // content, imageId, userId(세션)
		System.out.println("진입함 ~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("content: " + content);
		Comment commentEntity = commentService.댓글쓰기(principalDetails.getUser(), content, imageId);
		
		return new CMRespDto<>(1, commentEntity);
	}
}