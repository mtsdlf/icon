package com.alkemy.icon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icon.dto.ContinentDTO;
import com.alkemy.icon.entity.ContinentEntity;
import com.alkemy.icon.entity.CityEntity;
import com.alkemy.icon.exceptions.ParamNotFound;
import com.alkemy.icon.mapper.ContinentMapper;
import com.alkemy.icon.repository.ContinentRepository;
import com.alkemy.icon.service.ContinentService;

@Service
public class ContinentServiceImpl implements ContinentService{
	
	private ContinentRepository continentRepository;
	
	private ContinentMapper continentMapper;
	
	@Autowired
	public ContinentServiceImpl(ContinentRepository continentRepository, ContinentMapper continentMapper) {
		this.continentRepository = continentRepository;
		this.continentMapper = continentMapper;
	}

	@Override
	public List<ContinentDTO> getAll() {
		List<ContinentEntity> entities = continentRepository.findAll();
		List<ContinentDTO> result = continentMapper.continentEntityList2DTOList(entities);
		return result;
	}

	@Override
	public ContinentDTO getDetailsById(Long id) {
		Optional<ContinentEntity> entity = Optional.of(continentRepository.getById(id));
		if (!entity.isPresent()) {
			throw new ParamNotFound("continent id not valid");
		}
		ContinentDTO continentDTO = this.continentMapper.continentEntity2DTO(entity.get());
		return continentDTO;
	}
	
	@Override
	public ContinentDTO update(Long id, ContinentDTO continent) {
		Optional<ContinentEntity> oldEntity = Optional.of(this.continentRepository.getById(id));
		if (!oldEntity.isPresent()) {
			throw new ParamNotFound("icon id not valid");
		}
		ContinentEntity newEntity = continentMapper.continentDTO2Entity(continent);
		newEntity.setId(oldEntity.get().getId());
		ContinentEntity entitySaved = continentRepository.save(newEntity);
		ContinentDTO result = continentMapper.continentEntity2DTO(entitySaved);
		return result;
	}
	
	@Override
	public ContinentDTO save(ContinentDTO dto) {
		ContinentEntity entity = continentMapper.continentDTO2Entity(dto);
		ContinentEntity entitySaved = continentRepository.save(entity);
		ContinentDTO result = continentMapper.continentEntity2DTO(entitySaved);
		return result;
	}
	
	@Override
	public void delete(Long id) {
		this.continentRepository.deleteById(id);	
	}

}
