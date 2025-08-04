package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.KickOff;

public interface KickOffRepository extends JpaRepository<KickOff, String> {

	KickOff findBykickOffId(String kickOffId);
	
}
