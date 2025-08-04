package com.project.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.KickOff;

public interface KickOffRepository extends JpaRepository<KickOff, String> {

	KickOff findBykickOffId(String kickOffId);
	
	
  Page<KickOff> findByCompanyIdAndProjectNameContainingIgnoreCase(String companyId,String projectName ,Pageable pageable);
	
}
