package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.KickOffItems;

public interface KickOffItemsRepository extends JpaRepository<KickOffItems, String> {
	
	List<KickOffItems> findByKickOffId(String kickOffId);

}
