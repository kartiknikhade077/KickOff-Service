package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entity.KickOffItems;

public interface KickOffItemsRepository extends JpaRepository<KickOffItems, String> {
	
	List<KickOffItems> findByKickOffId(String kickOffId);

	
	@Query("SELECT k.itemNo FROM KickOffItems k JOIN KickOff ko ON k.kickOffId = ko.kickOffId WHERE ko.projectId = :projectId")
	List<Integer> findItemNoByProjectId(@Param("projectId") String projectId);
	
	
	@Query("SELECT partName FROM KickOffItems WHERE itemNo = :itemNo")
	String findPartNameByItemNo(@Param("itemNo") int itemNo);

}
