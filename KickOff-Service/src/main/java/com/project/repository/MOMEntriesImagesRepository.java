package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entity.MOMEntriesImages;

import jakarta.transaction.Transactional;

public interface MOMEntriesImagesRepository extends JpaRepository<MOMEntriesImages, String> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM MOMEntriesImages m WHERE m.momEntryId = :momEntryId")
	void deleteByMomEntryId(String momEntryId);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM MOMEntriesImages m WHERE m.momEntryId IN :momEntryIds")
	void deleteBulkMomEntryIds(@Param("momEntryIds") List<String> momEntryIds);


	List<MOMEntriesImages> findByMomEntryId(String momEntryId);


	List<MOMEntriesImages> findByMomEntryIdAndType(String momEntryId, String string);


}
