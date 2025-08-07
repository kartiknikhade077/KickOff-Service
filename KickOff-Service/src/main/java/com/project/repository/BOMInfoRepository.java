package com.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.BOMInfo;

public interface BOMInfoRepository extends JpaRepository<BOMInfo, String> {
	
	Page<BOMInfo> findByCompanyIdAndProjectNameContainingIgnoreCase(String companyId,String projectName,Pageable pageable);

}
