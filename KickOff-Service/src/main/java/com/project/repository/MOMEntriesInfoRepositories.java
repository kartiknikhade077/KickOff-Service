package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.MOMEntries;

public interface MOMEntriesInfoRepositories extends JpaRepository<MOMEntries, String> {
	
	List<MOMEntries>deleteByWorkOrderNo(String wrokOrderNo);
	

}
