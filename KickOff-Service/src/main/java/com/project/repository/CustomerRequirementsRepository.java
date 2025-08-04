package com.project.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.CustomerRequirements;

public interface CustomerRequirementsRepository extends JpaRepository<CustomerRequirements, String> {

	List<CustomerRequirements> findByKickOffId(String id);
}
