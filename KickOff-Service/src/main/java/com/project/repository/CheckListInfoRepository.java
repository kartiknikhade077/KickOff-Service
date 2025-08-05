package com.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.CheckListInfo;

public interface CheckListInfoRepository  extends JpaRepository<CheckListInfo, String>{
	
	CheckListInfo findByCheckListId(String checkListId);
	
	 Page<CheckListInfo> findByCompanyIdAndProjectNameContainingIgnoreCase(String companyId,String projectName ,Pageable pageable);

}
