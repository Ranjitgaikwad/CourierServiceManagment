package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.app.dao.BranchRepository;
import com.app.dao.CourierRepository;
import com.app.dao.UserRepository;
import com.app.exc_handler.ResourceNotFoundException;
import com.app.pojo.Branch;
import com.app.pojo.Courier;
import com.app.pojo.JwtRequest;
import com.app.pojo.JwtResponse;
import com.app.pojo.Role;
import com.app.pojo.User;
import com.app.service.JwtUserDetailsService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourierRepository courierRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public AdminController() {
		System.out.println("in ctor of : " + getClass().getName());
	}

	// =>signin for ADMIN --> functionality tested on postman

//	@PostMapping("/login") // =>signin for ADMIN --> functionality tested on postman
//	public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> request) {
//		System.out.println("in auth " + request);
//		String email = request.get("email");
//		String password = request.get("password");
//		System.out.println(email);
//		System.out.println(password);
////		Map<String, String> map = new HashMap<>();
////		map.put("msg", "Invalid Credentials");
//		User user = userRepository.authenticateUser(email, password);
//		System.out.println("User : " + user);
//		if (user!=null && user.getRole()==Role.ADMIN) {
//			return new ResponseEntity<>("Authenticated Successfull!", HttpStatus.OK);
//		}
//		else// API : o.s.http.ResponseEntity<T> (T body,HttpStatus stsCode)
//			return new ResponseEntity<>("Authenticated notttttt Successfull!", HttpStatus.UNAUTHORIZED);
//		
//	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		user.setRole(Role.ADMIN);
		return new ResponseEntity<>(userDetailsService.save(user), (HttpStatus.OK));
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

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	// get all user

	@GetMapping("/user_list")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// create user rest api

	@PostMapping("/add_employee")
	public User createEmployee(@RequestBody User user) {
		// user.setPassword(bcryptEncoder.encode(user.getPassword()));
		System.out.println((user.getPassword()));
		user.setRole(Role.EMPLOYEE);
		return userRepository.save(user);
	}
	
	//get employee by id
	@GetMapping("/employee/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not exist with id :" + id));
		return ResponseEntity.ok(user);
	}

	// delete user rest api
	@DeleteMapping("/employee_del/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	// update employee by id:

	@PutMapping("/employee_updt/{id}")
	public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not exist with id :" + id));

		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());

		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	// left to be implemented.............
	// get user by id rest api
	@GetMapping("/track/{trackingid}")
	public ResponseEntity<Courier> getCourierBytrackingId(@PathVariable String trackingId) {
		Courier courier = courierRepository.findBytrackingId(trackingId);

		// .orElseThrow(() -> new ResourceNotFoundException("courier not exist with
		// tracking id :" + trackingId));
		return ResponseEntity.ok(courier);
	}

	
	@PostMapping("/add_emp/{id}")
	public String updateUserDetails(@PathVariable Integer id, @RequestBody User userDetails) {
	//	System.out.println("USER  : "+getClass().getName());
		User user = userRepository.getOne(id);
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setRole(userDetails.getRole());
		user.setPassword(bcryptEncoder.encode(userDetails.getPassword()));  
	//	System.out.println("Before: "+a.getPassword()+" After: "+bcryptEncoder.encode(a.getPassword()));
		//System.out.println("Here " + admin);
		userRepository.save(user);
		return userDetails.getFirstName()+" updated successfully";
	}
	
	
	@PostMapping(value = "/addbranch")
	public ResponseEntity<?> saveBranch(@RequestBody Branch branch) throws Exception {
		//user.setRole(Role.ADMIN);
		return ResponseEntity.ok(branchRepository.save(branch));
	}
	
	@GetMapping("/allbranch")
	public List<Branch> getAllBranchs(){
		return branchRepository.findAll();
	}		
	
	
	@GetMapping("/getcourier")
	public List<Courier> getAllCourier(){
		return courierRepository.findAll();
	}
	
	@GetMapping("/courier/{id}")
	public ResponseEntity<Courier> getCourierById(@PathVariable Integer id) {
		Courier courier = courierRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("courier not exist with id :" + id));
		return ResponseEntity.ok(courier);
	
}
}


