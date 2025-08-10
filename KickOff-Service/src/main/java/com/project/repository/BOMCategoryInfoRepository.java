package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.BOMCategoryInfo;

public interface BOMCategoryInfoRepository  extends JpaRepository<BOMCategoryInfo, String>{
	
	List<BOMCategoryInfo> findByBomId(String bomId);

}
