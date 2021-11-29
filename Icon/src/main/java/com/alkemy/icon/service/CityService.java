package com.alkemy.icon.service;

import java.util.List;

import com.alkemy.icon.dto.CityBasicDTO;
import com.alkemy.icon.dto.CityDTO;

public interface CityService {
	
	List<CityBasicDTO> getAll();
	
	CityDTO getDetailsById(Long id);
	
	List<CityDTO> getByFilters(String name, Long continent, String order);
	
	CityDTO update(Long id, CityDTO icon);
	
	CityDTO save(CityDTO location);

	void addIcon(Long id, Long idIcon);
	
	void removeIcon(Long id, Long idIcon);
	
	void delete(Long id);

	
}
