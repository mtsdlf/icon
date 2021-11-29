package com.alkemy.icon.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icon.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

	List<CityEntity> findAll(Specification<CityEntity> spec);
	
}
