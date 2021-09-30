package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.app.pojo.Courier;


@EnableJpaRepositories
public interface CourierRepository extends JpaRepository<Courier, Integer> {

	Courier findBytrackingId(String trackingId);

	//void findBycourierId(int courierId);
	//Optional<Courier> findbytrackingId(String trackingId);
	//Optional<Courier> findBycourierId(int courierId);
	Courier findByCourierId(int courierId);
}
