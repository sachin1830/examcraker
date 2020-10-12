package com.exam.onlineexam.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.onlineexam.model.Question;
import com.exam.onlineexam.repository.AdminRepository;

@Service
public class AdminService 
{
	@Autowired
	AdminRepository adminRepository;
	
	public void addQuestion(Question question)
	{
		adminRepository.save(question);
		
	}
	
	public List<Question> getAllQuestions()
	{
		return adminRepository.findAll();
	}
	
	public Question getQuestionById(int id)
	{
	   Optional<Question>optional=adminRepository.findById(id);
	   
	   Question question=null;
	   if(optional.isPresent())
		{
			return question=optional.get();
		}
		else
		{
			throw new RuntimeException("question not found");
		}
	}
	
	public void deleteQuestion(int id)
	{
		adminRepository.deleteById(id);
	}
	
}
