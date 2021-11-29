package com.alkemy.icon.service;

import java.util.List;

import com.alkemy.icon.dto.LocationBasicDTO;
import com.alkemy.icon.dto.LocationDTO;

public interface LocationService {
	
	List<LocationBasicDTO> getAll();
	
	LocationDTO getDetailsById(Long id);
	
	List<LocationDTO> getByFilters(String name, Long continent, String order);
	
	LocationDTO update(Long id, LocationDTO icon);
	
	LocationDTO save(LocationDTO location);

	void addIcon(Long id, Long idIcon);
	
	void removeIcon(Long id, Long idIcon);
	
	void delete(Long id);

	
}
