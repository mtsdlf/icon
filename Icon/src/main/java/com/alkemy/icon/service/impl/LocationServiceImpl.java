package com.alkemy.icon.service.impl;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.dto.LocationBasicDTO;
import com.alkemy.icon.dto.LocationDTO;
import com.alkemy.icon.dto.LocationFiltersDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.entity.LocationEntity;
import com.alkemy.icon.exceptions.ParamNotFound;
import com.alkemy.icon.mapper.LocationMapper;
import com.alkemy.icon.repository.LocationRepository;
import com.alkemy.icon.repository.specification.LocationSpecification;
import com.alkemy.icon.service.IconService;
import com.alkemy.icon.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	private LocationRepository locationRepository;
	
	private LocationSpecification locationSpecification;
	
	private LocationMapper locationMapper;
	
	private IconService iconService;
	
	@Autowired
	public LocationServiceImpl(
			LocationRepository locationRepository,
			LocationSpecification locationSpecification,
			LocationMapper locationMapper,
			IconService iconService
	) {
		this.locationRepository = locationRepository;
		this.locationSpecification = locationSpecification;
		this.locationMapper = locationMapper;
		this.iconService = iconService;
	}

	@Override
	public List<LocationBasicDTO> getAll() {
		List<LocationEntity> entities = locationRepository.findAll();
		List<LocationBasicDTO> iconBasicDTOS = locationMapper.locationEntityList2BasicDTOList(entities);
		return iconBasicDTOS;
	}
	
	@Override
	public LocationDTO getDetailsById(Long id) {
		Optional<LocationEntity> entity = Optional.of(locationRepository.getById(id));
		if (!entity.isPresent()) {
			throw new ParamNotFound("id location not valid");
		}
		LocationDTO locationDTO = this.locationMapper.locationEntity2DTO(entity.get(), true);
		return locationDTO;
	}
	
	@Override public List<LocationDTO> getByFilters(String name, Long continent, String order) { 
		LocationFiltersDTO filtersDTO = new LocationFiltersDTO(name, continent, order);
		List<LocationEntity> entities = this.locationRepository.findAll(this.locationSpecification.getByFilters(filtersDTO));
		List<LocationDTO> dtos = this.locationMapper.locationEntitySet2DTOList(entities, true);
		return dtos;
	} 
	@Override
	public LocationDTO update(Long id, LocationDTO icon) {
		Optional<LocationEntity> entity = this.locationRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("location id not valid");
		}
		this.locationMapper.locationEntityRefreshValues(entity.get(), icon);
		LocationEntity updatedEntity = this.locationRepository.save(entity.get());
		LocationDTO result = this.locationMapper.locationEntity2DTO(updatedEntity, true);
		return result;
	}
	
	@Override
	public LocationDTO save(LocationDTO dto) {
		LocationEntity entity = locationMapper.locationDTO2Entity(dto);
		LocationEntity entitySaved = locationRepository.save(entity);
		LocationDTO result = locationMapper.locationEntity2DTO(entitySaved, true);
		return result;
	}

	@Override
	public void addIcon(Long id, Long idIcon) {
		LocationEntity entity = this.locationRepository.getById(id);
		entity.getIcons().size();
		IconEntity iconEntity = this.iconService.getEntityById(idIcon);
		entity.addIcon(iconEntity);
		this.locationRepository.save(entity);
	}

	@Override
	public void removeIcon(Long id, Long idIcon) {
		LocationEntity entity = this.locationRepository.getById(id);
		entity.getIcons().size();
		IconEntity iconEntity = this.iconService.getEntityById(idIcon);
		entity.removeIcon(iconEntity);
		this.locationRepository.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		this.locationRepository.deleteById(id);	
	}

}
