package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.ItemProcess;

public interface itemProcessRepository extends JpaRepository<ItemProcess, String> {
	
	List<ItemProcess> findByKickOffId(String kickOffId);

}
