package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.CourierRepository;
import com.app.exc_handler.ResourceNotFoundException;
import com.app.pojo.Courier;
import com.app.pojo.User;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/courier")
public class CourierController {
	@Autowired
	private CourierRepository courierRepository;

	// get all user
	@GetMapping("/getcourier")
	public List<Courier> getAllUsers() {
		return courierRepository.findAll();
	}

	// create user rest api
	@PostMapping("/addcourier")
	public Courier CreateCourier(@RequestBody Courier courier) {
		return courierRepository.save(courier);
	}

	// get user by id rest api
	@GetMapping("/courier/{id}")
	public ResponseEntity<Courier> getCourierById(@PathVariable Integer id) {
		Courier courier = courierRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("courier not exist with id :" + id));
		return ResponseEntity.ok(courier);

	}

	// delete user rest api
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
