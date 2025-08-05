package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.CheckListCategory;

public interface CheckListCategoryRepository extends JpaRepository<CheckListCategory, String> {

	List<CheckListCategory> findByCompanyIdOrderBySequence(String companyId);
}
