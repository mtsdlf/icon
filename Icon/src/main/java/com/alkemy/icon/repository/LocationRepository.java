package com.alkemy.icon.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icon.entity.LocationEntity;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

	List<LocationEntity> findAll(Specification<LocationEntity> spec);
	
}
