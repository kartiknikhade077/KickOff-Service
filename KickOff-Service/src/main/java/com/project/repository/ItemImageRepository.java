package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.ItemsImages;

import jakarta.transaction.Transactional;

public interface ItemImageRepository extends JpaRepository<ItemsImages, String> {
	
	
	List<ItemsImages> findByItemIdIn(List<String> itemIds);
	
	@Transactional
	void deleteByItemId(String itemId);


}
