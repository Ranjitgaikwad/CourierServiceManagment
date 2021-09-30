package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.BranchRepository;
import com.app.dao.CourierRepository;
import com.app.dao.UserRepository;
import com.app.pojo.Courier;
import com.app.pojo.Role;
import com.app.pojo.User;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourierRepository courierRepository;
	@Autowired
	private BranchRepository branchRepository;
	
	public CustomerController() {
		System.out.println("in ctor of : "+getClass().getName());
	}
	
	// =>signin for Customer --> functionality tested on postman
	
//	@PostMapping("/login") // =>signin for Customer --> functionality tested on postman
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
//		if (user!=null && user.getRole()==Role.CUSTOMER) {
//			return new ResponseEntity<>("Authenticated Successfull!", HttpStatus.OK);
//		}
//		else// API : o.s.http.ResponseEntity<T> (T body,HttpStatus stsCode)
//			return new ResponseEntity<>("Authenticated notttttt Successfull!", HttpStatus.UNAUTHORIZED);
//		
//	}
	        // add courier
		@PostMapping("/addcourier")
			public Courier CreateCourier(@RequestBody Courier courier) {
				return courierRepository.save(courier);
			}
	
		@GetMapping("/track/{trackingid}")
			public ResponseEntity<Courier> getCourierBytrackingId(@PathVariable String trackingId) {
				Courier courier = courierRepository.findBytrackingId(trackingId);
						
						//.orElseThrow(() -> new ResourceNotFoundException("courier not exist with tracking id :" + trackingId));
				return ResponseEntity.ok(courier);
			}
}
