package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.ItemProcess;

public interface itemProcessRepository extends JpaRepository<ItemProcess, String> {
	
	List<ItemProcess> findByKickOffIdOrderBySequence(String kickOffId);
 
	List<ItemProcess> findByEmployeeId(String employeeId);
	
	@Query("SELECT k.workOrderNumber FROM ItemProcess k  WHERE k.itemNo = :itemNo")
	List<String> findWorkOrderNumberByItemNo(int itemNo);
	
    ItemProcess findByWorkOrderNumber(String workOrderNumber);
}
