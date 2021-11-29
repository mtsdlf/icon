package com.alkemy.icon.service;

import java.util.List;
import java.util.Set;

import com.alkemy.icon.dto.IconBasicDTO;
import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.entity.LocationEntity;

public interface IconService {
	
	List<IconBasicDTO> getAll();
	
	IconEntity getEntityById(Long id);
	
	IconDTO getDetailsById(Long id);
	
	List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order);
	
	IconDTO update(Long id, IconDTO icon);
	
	IconDTO save(IconDTO icon);
	
	void delete(Long id);
	
}
