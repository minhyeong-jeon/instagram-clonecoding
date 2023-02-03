package com.example.instagram.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.instagram.domain.user.User;
import com.example.instagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 진행중");
		
		User principal = userRepository.findByUsername(username);
		
		if (principal == null) {
			return null;
		} else {
			System.out.println("누가 로그인됐냐" + principal);
			System.out.println("누가 로그인됐냐" + username);
			
			return new PrincipalDetails(principal);
		}
		
	
	} //UserDetailsService 가 이미 ioc에 등록되어있는데 ioc는 싱글톤 패턴이라서 PrincipalDetailsService을 띄우면 중복이 되는데 뒤에거는 갈아 엎어버린다. 그래서 PrincipalDetailsService가 뜬다.
		//로그인을 하면 loadUserByUsername가 때려짐
	//principal : 인증된 사용자의 객체로 많이 사용됨
}
