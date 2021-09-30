package com.app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.UserDTO;
import com.app.config.JwtTokenUtil;
import com.app.dao.UserRepository;
import com.app.exc_handler.ResourceNotFoundException;
import com.app.pojo.JwtRequest;
import com.app.pojo.JwtResponse;
import com.app.pojo.Role;
import com.app.pojo.User;
import com.app.service.JwtUserDetailsService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
     @Autowired
	private JwtUserDetailsService userDetailsService;

	// get all user
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		user.setRole(Role.CUSTOMER);
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	// create user rest api
	@PostMapping("/user")
	public User createEmployee(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	// get user by id rest api
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not exist with id :" + id));
		return ResponseEntity.ok(user);
	}
	
	// update user rest api
	
	@PutMapping("/emp_updt/{id}")
	public ResponseEntity<User> updateEmployee(@PathVariable Integer id, @RequestBody User userDetails){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not exist with id :" + id));
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	// delete user rest api
	@DeleteMapping("/employee_del/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Integer id){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
