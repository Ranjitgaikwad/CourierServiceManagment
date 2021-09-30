package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.DTO.UserDTO;
import com.app.dao.UserRepository;
import com.app.pojo.User;



@Service

public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override  //load by username method extends user details service 
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	   User user = userRepo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}
	
	public  User save(UserDTO user) {
		 User newUser = new  User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		
		return userRepo.save(newUser);
	}
}