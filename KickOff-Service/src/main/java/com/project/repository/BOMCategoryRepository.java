package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.BOMCategory;

public interface BOMCategoryRepository extends JpaRepository<BOMCategory, String> {
	
	List<BOMCategory> findByCategoryTypeAndCompanyId(String categoryType ,String companyId);
	
	List<BOMCategory> findByCompanyId(String companyId);

}
