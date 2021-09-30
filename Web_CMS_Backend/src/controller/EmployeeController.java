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
import com.app.pojo.Courier;
import com.app.pojo.JwtRequest;
import com.app.pojo.JwtResponse;
import com.app.pojo.Role;
import com.app.service.JwtUserDetailsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourierRepository courierRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
     @Autowired
	private JwtUserDetailsService userDetailsService;

	public EmployeeController() {
		System.out.println("in ctor of : "+getClass().getName());
	}
	
	// =>signin for Employee --> functionality tested on postman
	
//	@PostMapping("/login") // =>signin for Employee --> functionality tested on postman
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
//		if (user!=null && user.getRole()==Role.EMPLOYEE) {
//			return new ResponseEntity<>("Authenticated Successfull!", HttpStatus.OK);
//		}
//		else// API : o.s.http.ResponseEntity<T> (T body,HttpStatus stsCode)
//			return new ResponseEntity<>("Authenticated notttttt Successfull!", HttpStatus.UNAUTHORIZED);
//		
//	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		user.setRole(Role.EMPLOYEE);
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
	@GetMapping("/getcourier")
	public List<Courier> getAllCourier() {
		return courierRepository.findAll();
	}
	
	// add courier
		@PostMapping("/addcourier")
		public Courier CreateCourier(@RequestBody Courier courier) {
			return courierRepository.save(courier);
		}
		
		@GetMapping("/courier/{id}")
		public ResponseEntity<Courier> getUserById(@PathVariable int courierId) {
			Courier courier = courierRepository.findById(courierId)
					.orElseThrow(() -> new ResourceNotFoundException("courier not exist with id :" + courierId));
			return ResponseEntity.ok(courier);
		}
		// delete courier by id
		@DeleteMapping("/delcourier/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Integer id) {
			Courier courier = courierRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Courier not exist with given id :" + id));

			courierRepository.delete(courier);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
