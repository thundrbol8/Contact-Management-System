package com.contactManagerSystem;

import java.util.*;

import org.springframework.web.bind.annotation.*;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class myController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("user" , new Contact());
		return "home";
	}
	
	
	@GetMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("user" , new Contact());
		return "signup";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") Contact user , Model model) {
		
		userRepository.save(user);
		return "home";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") Contact user , Model model) {
		
		Contact validation = userRepository.findByEmailAndPassword(user.email , user.password);
		
		if(validation != null) {
		
			List<Contact> contactResults = new ArrayList<Contact>();
			List<Email> contactEmails = emailRepository.findAllByUseremail(user.email);
			
			for(int i = 0 ; i < contactEmails.size(); ++i) {
				contactResults.add(userRepository.findByEmail(contactEmails.get(i).contactemail));
			}
			
			model.addAttribute("userEmail" , user.email);
			model.addAttribute("contactResults" , contactResults);
			
			return "dashboard";
		}
		
		return "redirect:/";
	}
	
	
	@PostMapping("/createContact")
	public String createContact(@RequestParam String userEmail , @ModelAttribute("user") Contact user , Model model)  {
		
		userRepository.save(user);
		Email emailObj = new Email();
		emailObj.setUseremail(userEmail);
		emailObj.setContactemail(user.email);
		emailRepository.save(emailObj);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/create")
	public String create(@RequestParam String userEmail , Model model , @ModelAttribute("user") Contact user) {
		
		model.addAttribute("userEmail" , userEmail);
		
		return "createContact";
	}	
	
	
	@PostMapping("/updateContact")
	public String updateContact(@RequestParam String contactEmail , @ModelAttribute("user") Contact user , Model model) {
		
		userRepository.deleteByEmail(contactEmail);
		user.setEmail(contactEmail);
		userRepository.save(user);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/update")
	public String update(@RequestParam String contactEmail , Model model , @ModelAttribute("user") Contact user) {
		
		model.addAttribute("contactEmail" , contactEmail);
		
		return "updateContact";
	}
	
	
	@GetMapping("/deleteContact")
	public String deleteContact(@RequestParam String contactEmail , @RequestParam String userEmail) {
		
		userRepository.deleteByEmail(contactEmail);
		emailRepository.deleteByUseremailAndContactemail(userEmail , contactEmail);
		
		return "redirect:/";
	}
}

