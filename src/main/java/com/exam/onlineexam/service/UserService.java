package com.exam.onlineexam.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.onlineexam.model.Question;
import com.exam.onlineexam.model.RegisterUser;
import com.exam.onlineexam.model.Role;
import com.exam.onlineexam.model.RoleAssign;
import com.exam.onlineexam.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired 
	UserRepository userRepository;
	@Autowired
	AdminService adminService;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public String checkAnswer(int id,String selected_answer)
	{
		Question question=adminService.getQuestionById(id);
		if(question.getAnnswer().equals(selected_answer))
		{
			return "Success";
		}
		else
		{
			return "failed";
		}
		
	}
	
	public void saveUser(RegisterUser user)
	{
		RegisterUser newuser=new RegisterUser(user.getId(),user.getFirst_name(),user.getLast_name(),user.getEmail()
				                      ,passwordEncoder.encode(user.getPassword()),Arrays.asList(new Role(RoleAssign.ROLE_USER.toString())));
		
		userRepository.save(newuser);
	}
}
