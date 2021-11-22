package com.alkemy.icon.service;

import java.util.List;

import com.alkemy.icon.dto.ContinentDTO;

public interface ContinentService {
	
	ContinentDTO getDetailsById(Long id);
	
	List<ContinentDTO> getAll();
	
	ContinentDTO save(ContinentDTO continent);
	
	ContinentDTO update(Long id, ContinentDTO continent);
	
	void delete(Long id);
}
