package com.alkemy.icon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icon.dto.ContinentDTO;
import com.alkemy.icon.entity.ContinentEntity;
import com.alkemy.icon.mapper.ContinentMapper;
import com.alkemy.icon.repository.ContinentRepository;
import com.alkemy.icon.service.ContinentService;

@Service
public class ContinentServiceImpl implements ContinentService{
	
	@Autowired
	private ContinentRepository continentRepository;
	
	@Autowired
	private ContinentMapper continentMapper;
	
	public ContinentDTO save(ContinentDTO dto) {
		ContinentEntity entity = continentMapper.continent2Entity(dto);
		ContinentEntity entitySaved = continentRepository.save(entity);
		ContinentDTO result = continentMapper.continentEntity2dto(entitySaved);
		return result;
	}

	@Override
	public List<ContinentDTO> getAllContinents() {
		List<ContinentEntity> entities = continentRepository.findAll();
		List<ContinentDTO> result = continentMapper.continentEntityList2dtoList(entities);
		return result;
	}
	
	
	

}
