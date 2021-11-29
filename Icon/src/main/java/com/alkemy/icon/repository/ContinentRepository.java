package com.alkemy.icon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icon.entity.ContinentEntity;

@Repository
public interface ContinentRepository extends JpaRepository<ContinentEntity, Long> {
	
}
