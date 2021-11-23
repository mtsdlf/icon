package com.alkemy.icon.service;

import java.util.List;
import java.util.Set;

import com.alkemy.icon.dto.IconBasicDTO;
import com.alkemy.icon.dto.IconDTO;

public interface IconService {
	
	IconDTO getDetailsById(Long id);
	
	List<IconBasicDTO> getAll();
	
	//List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order);
	
	IconDTO save(IconDTO icon);
	
	IconDTO update(Long id, IconDTO icon);
	
	void addLocation(Long id, Long idLocation);
	
	void removeLocation(Long id, Long idLocation);
	
	void delete(Long id);
}
