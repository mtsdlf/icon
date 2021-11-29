package com.alkemy.icon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icon.dto.CityBasicDTO;
import com.alkemy.icon.dto.CityDTO;
import com.alkemy.icon.dto.CityFiltersDTO;
import com.alkemy.icon.entity.CityEntity;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.exceptions.ParamNotFound;
import com.alkemy.icon.mapper.CityMapper;
import com.alkemy.icon.repository.CityRepository;
import com.alkemy.icon.repository.specification.CitySpecification;
import com.alkemy.icon.service.CityService;
import com.alkemy.icon.service.IconService;

@Service
public class CityServiceImpl implements CityService{

	private CityRepository cityRepository;
	
	private CitySpecification citySpecification;
	
	private CityMapper cityMapper;
	
	private IconService iconService;
	
	@Autowired
	public CityServiceImpl(
			CityRepository cityRepository,
			CitySpecification citySpecification,
			CityMapper cityMapper,
			IconService iconService
	) {
		this.cityRepository = cityRepository;
		this.citySpecification = citySpecification;
		this.cityMapper = cityMapper;
		this.iconService = iconService;
	}

	@Override
	public List<CityBasicDTO> getAll() {
		List<CityEntity> entities = cityRepository.findAll();
		List<CityBasicDTO> iconBasicDTOS = cityMapper.citiesEntityList2BasicDTOList(entities);
		return iconBasicDTOS;
	}
	
	@Override
	public CityDTO getDetailsById(Long id) {
		Optional<CityEntity> entity = Optional.of(cityRepository.getById(id));
		if (!entity.isPresent()) {
			throw new ParamNotFound("id city not valid");
		}
		CityDTO cityDTO = this.cityMapper.cityEntity2DTO(entity.get(), true);
		return cityDTO;
	}
	
	@Override public List<CityDTO> getByFilters(String name, Long continent, String order) { 
		CityFiltersDTO filtersDTO = new CityFiltersDTO(name, continent, order);
		List<CityEntity> entities = this.cityRepository.findAll(this.citySpecification.getByFilters(filtersDTO));
		List<CityDTO> dtos = this.cityMapper.cityEntitySet2DTOList(entities, true);
		return dtos;
	} 
	@Override
	public CityDTO update(Long id, CityDTO icon) {
		Optional<CityEntity> entity = this.cityRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("city id not valid");
		}
		this.cityMapper.cityEntityRefreshValues(entity.get(), icon);
		CityEntity updatedEntity = this.cityRepository.save(entity.get());
		CityDTO result = this.cityMapper.cityEntity2DTO(updatedEntity, true);
		return result;
	}
	
	@Override
	public CityDTO save(CityDTO dto) {
		CityEntity entity = cityMapper.cityDTO2Entity(dto);
		CityEntity entitySaved = cityRepository.save(entity);
		CityDTO result = cityMapper.cityEntity2DTO(entitySaved, true);
		return result;
	}

	@Override
	public void addIcon(Long id, Long idIcon) {
		CityEntity entity = this.cityRepository.getById(id);
		entity.getIcons().size();
		IconEntity iconEntity = this.iconService.getEntityById(idIcon);
		entity.addIcon(iconEntity);
		this.cityRepository.save(entity);
	}

	@Override
	public void removeIcon(Long id, Long idIcon) {
		CityEntity entity = this.cityRepository.getById(id);
		entity.getIcons().size();
		IconEntity iconEntity = this.iconService.getEntityById(idIcon);
		entity.removeIcon(iconEntity);
		this.cityRepository.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		this.cityRepository.deleteById(id);	
	}

}
