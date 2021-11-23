package com.alkemy.icon.service;

import java.util.List;

import com.alkemy.icon.dto.LocationBasicDTO;
import com.alkemy.icon.dto.LocationDTO;
import com.alkemy.icon.entity.LocationEntity;

public interface LocationService {
	
	LocationDTO getDetailsById(Long id);
	
	LocationEntity getEntityById(Long id);
	
	List<LocationBasicDTO> getAll();
	
	LocationDTO save(LocationDTO location);
	
	LocationDTO update(Long id, LocationDTO icon);

	void delete(Long id);
}
