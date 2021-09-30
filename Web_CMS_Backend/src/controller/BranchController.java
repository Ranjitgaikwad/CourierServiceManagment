package com.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.BranchRepository;
import com.app.exc_handler.ResourceNotFoundException;
import com.app.pojo.Branch;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/branches")

public class BranchController {

	private BranchRepository branchRepository;
	
	@GetMapping("/branch")
	public List<Branch> getAllBranchs(){
		return branchRepository.findAll();
	}		
	
	// get branch by id rest api
	@GetMapping("/branch/{id}")
	public ResponseEntity<Branch> getUserById(@PathVariable int id) {
		Branch branch = branchRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("user not exist with id :" + id));
		return ResponseEntity.ok(branch);
	}
		
}
