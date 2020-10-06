package com.exam.onlineexam.securityconfig;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.onlineexam.model.RegisterUser;
import com.exam.onlineexam.model.Role;
import com.exam.onlineexam.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService
{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Username is :"+username);
		
		RegisterUser user=userRepository.findByEmail(username);
		
		if(user==null)
		{
			System.out.println("user is null");
			throw new  UsernameNotFoundException("invalid username or password!!");
		}
		
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(mapRolesToAuthorities(user.getRoles()));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));	
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole_name())).collect(Collectors.toList());
	}
	
	

}
