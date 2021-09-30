package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findById(Long id);

	@Query("select u from User u where u.email=:em and password=:pswd")
	User authenticateUser(@Param("em") String email, @Param("pswd") String password);

	//Object findBytrackingId(Integer trackingId);
	User findByEmail(String email);


}
