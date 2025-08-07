package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.CheckListItemsInfo;

public interface CheckListItemsInfoRepository extends JpaRepository<CheckListItemsInfo, String> {
	
       	List<CheckListItemsInfo> findByCheckListId(String checkListId);

		void deleteByCheckListId(String checkListId);

}
