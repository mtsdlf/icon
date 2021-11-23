package com.alkemy.icon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icon.entity.IconEntity;

@Repository
public interface IconRepository extends JpaRepository<IconEntity, Long> {
	

}
