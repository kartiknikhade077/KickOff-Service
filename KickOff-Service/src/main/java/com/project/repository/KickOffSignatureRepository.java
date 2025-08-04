package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KickOffSignatureRepository extends JpaRepository<KickOffSignature, String> {

	List<KickOffSignature> findByKickOffId(String kickOffId);
}
