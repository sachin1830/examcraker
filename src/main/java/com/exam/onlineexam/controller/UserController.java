package com.exam.onlineexam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.exam.onlineexam.model.Question;
import com.exam.onlineexam.model.RegisterUser;
import com.exam.onlineexam.service.AdminService;
import com.exam.onlineexam.service.UserService;

@Controller
public class UserController 
{
	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String homeMapping()
	{
		return "index";
	}
	
	@GetMapping("/programmingExam")
	public String programmingExam(Model model)
	{
		
		List<Question> questionlist=adminService.getAllQuestions();
		model.addAttribute("questionlist", questionlist);
		model.addAttribute("type","programming");
		return "question";
		
	}
	
	@GetMapping("/aptitudeExam")
	public String aptitudeExam(Model model)
	{
		
		List<Question> questionlist=adminService.getAllQuestions();
		model.addAttribute("questionlist", questionlist);
		model.addAttribute("type","aptitude");
		return "question";
		
	}
	@GetMapping("/reasoningExam")
	public String reasoningExam(Model model)
	{
		
		
		List<Question> questionlist=adminService.getAllQuestions();
		model.addAttribute("questionlist", questionlist);
		model.addAttribute("type","reasoning");
		return "question";
	}
	
	@RequestMapping("/checkAnswer")
	@ResponseBody
	public String checkAnswer(HttpServletRequest request)
	{
		
		int id=Integer.parseInt(request.getParameter("uid"));
		String selected_answer=request.getParameter("select");
		String result=userService.checkAnswer(id, selected_answer);
		return result;
	}
	
	@GetMapping("/login")
	public String loginMapping()
	{
		return "login";
	}
	
	@GetMapping("/registration")
	public String registrationMapping(Model model)
	{
		RegisterUser user=new RegisterUser();
		model.addAttribute("newuser", user);
		return "registration";
	}
	
	@PostMapping("/userRegistration")
	public String userRegistration(@ModelAttribute("newuser")RegisterUser user)
	{
		
		userService.saveUser(user);	
		return "redirect:/";
	}
	
}
