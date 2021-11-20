package com.alkemy.icon.service;

import java.util.List;

import com.alkemy.icon.dto.ContinentDTO;

public interface ContinentService {
	
	ContinentDTO save(ContinentDTO dto);
	
	List<ContinentDTO> getAllContinents();
}
