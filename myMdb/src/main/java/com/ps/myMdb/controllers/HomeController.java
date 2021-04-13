package com.ps.myMdb.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ps.myMdb.dao.UserRepository;
import com.ps.myMdb.entities.User;

//import com.ps.pma.dao.EmployeeRepository;
//import com.ps.pma.dao.ProjectRepository;
//import com.ps.pma.dto.EmployeeProject;
//import com.ps.pma.entities.Employee;
//import com.ps.pma.entities.Project;

@Controller
public class HomeController {
	
//	@Autowired
//	ProjectRepository proRepo;
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) {
//		List<Project>  projects = proRepo.findAll();
//		model.addAttribute("projectsList",projects);
//		
//		List<Employee>  employees = emplRepo.findAll();
//		model.addAttribute("employeesList",employees);
		
//		List<EmployeeProject>  employeesProjectCount = emplRepo.employeeProjects();
//		model.addAttribute("employeesListProjectsCount", employeesProjectCount);
		
		return "main/home";
	}
	@GetMapping("/welcome")
	public String displayWelcome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		String username = userDetails.getUsername();
		User user = userRepo.findByUsername(username);
		model.addAttribute("user", user);
		return "main/welcome";
	}
}
