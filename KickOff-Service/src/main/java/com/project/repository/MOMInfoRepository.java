package com.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.MOMInfo;

public interface MOMInfoRepository extends JpaRepository<MOMInfo, String> {
	
	Page<MOMInfo> findByCompanyIdAndProjectNameContainingIgnoreCase(String companyId,String projectName,Pageable pageable );
 
}
