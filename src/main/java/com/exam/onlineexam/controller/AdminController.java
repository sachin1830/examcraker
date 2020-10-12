package com.exam.onlineexam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.onlineexam.model.Question;
import com.exam.onlineexam.service.AdminService;

@Controller
public class AdminController 
{
	@Autowired
	AdminService adminService;
	
	@GetMapping("/adminpanel")
	public String adminPanel(Model model)
	{
		Question question=new Question();
		model.addAttribute("newquestion", question);
		List<Question> questionlist=adminService.getAllQuestions();
		model.addAttribute("list_of_question",questionlist);
		return "admin";
	}
	
	@PostMapping("/addQuestion")
	public String addQuestion(@ModelAttribute("newquestion") Question question)
	{
		System.out.println(question);
		adminService.addQuestion(question);
		return "redirect:/adminpanel";
	}
	
	@GetMapping("/updateQuestion/{id}")
	public String updateQuestion(@PathVariable(value = "id") int id , Model model)
	{
		Question question=adminService.getQuestionById(id);
		
		model.addAttribute("newquestion", question);
		return "update";
		
	}
	
	@GetMapping("/deleteQuestion/{id}")
	public String deleteQuestion(@PathVariable(value = "id") int id)
	{
		adminService.deleteQuestion(id);
		return "redirect:/adminpanel";
		
	}
	
	
}
