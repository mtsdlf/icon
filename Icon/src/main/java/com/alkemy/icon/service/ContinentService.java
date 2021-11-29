package com.alkemy.icon.service;

import java.util.List;

import com.alkemy.icon.dto.ContinentDTO;

public interface ContinentService {
	
	List<ContinentDTO> getAll();
	
	ContinentDTO getDetailsById(Long id);
	
	ContinentDTO update(Long id, ContinentDTO continent);
	
	ContinentDTO save(ContinentDTO continent);
	
	void delete(Long id);
	
}
