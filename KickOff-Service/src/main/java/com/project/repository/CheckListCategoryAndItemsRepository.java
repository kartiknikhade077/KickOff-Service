package com.project.repository;

import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.CheckListCategoryAndItems;

public interface CheckListCategoryAndItemsRepository extends JpaRepository<CheckListCategoryAndItems, String> {
	
	List<CheckListCategoryAndItems> findByCompanyIdOrderBySequence(String companyId);

}