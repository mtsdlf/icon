package com.alkemy.icon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icon.dto.IconBasicDTO;
import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.entity.LocationEntity;
import com.alkemy.icon.exceptions.ParamNotFound;
import com.alkemy.icon.mapper.IconMapper;
import com.alkemy.icon.repository.IconRepository;
import com.alkemy.icon.repository.specification.IconSpecification;
import com.alkemy.icon.service.IconService;
import com.alkemy.icon.service.LocationService;

@Service
public class IconServiceImpl implements IconService{
	
	private IconRepository iconRepository;
	
	//private IconSpecification iconSpecification;
	
	private IconMapper iconMapper;
	
	private LocationService locationService;
	
	@Autowired
	public IconServiceImpl(
			IconRepository iconRepository, 
			//IconSpecification iconSpecification, 
			IconMapper iconMapper,
			LocationService locationService
	) {
		this.iconRepository = iconRepository;
		//this.iconSpecification = iconSpecification;
		this.iconMapper = iconMapper;
		this.locationService = locationService;
	}

	@Override
	public IconDTO save(IconDTO iconDTO) {
		IconEntity entity = iconMapper.iconDTO2Entity(iconDTO);
		IconEntity entitySaved = iconRepository.save(entity);
		IconDTO result = iconMapper.iconEntity2DTO(entitySaved, true);
		return result;
	}

	@Override
	public IconDTO getDetailsById(Long id) {
		Optional<IconEntity> entity = Optional.of(iconRepository.getById(id));
		if (!entity.isPresent()) {
			throw new ParamNotFound("icon id not valid");
		}
		IconDTO iconDTO = this.iconMapper.iconEntity2DTO(entity.get(), true);
		return iconDTO;
		
	}

	@Override
	public List<IconBasicDTO> getAll() {
		List<IconEntity> entities = this.iconRepository.findAll();
		List<IconBasicDTO> iconBasicDTOS = this.iconMapper.iconEntitySet2BasicDTOList(entities);
		return iconBasicDTOS;
	}

	/*
	 * @Override public List<IconDTO> getByFilters(String name, String date,
	 * Set<Long> cities, String order) { IconFiltersDTO filtersDTO = new
	 * IconFiltersDTO(name, date, cities, order); List<IconEntity> entities =
	 * this.iconRepository.findAll(this.iconSpecification.getByFilters(filtersDTO));
	 * //turns to false List<IconDTO> dtos =
	 * this.iconMapper.iconEntitySet2DTOList(entities, false); return dtos; }
	 */

	@Override
	public IconDTO update(Long id, IconDTO icon) {
		//TODO: More Exceptions
		Optional<IconEntity> oldEntity = Optional.of(this.iconRepository.getById(id));
		if (!oldEntity.isPresent()) {
			throw new ParamNotFound("icon id not valid");
		}
		IconEntity newEntity = iconMapper.iconDTO2Entity(icon);
		newEntity.setId(oldEntity.get().getId());
		IconEntity entitySaved = iconRepository.save(newEntity);
		IconDTO result = iconMapper.iconEntity2DTO(entitySaved, false);
		return result;
	}

	@Override
	public void addLocation(Long id, Long idLocation) {
		IconEntity entity = this.iconRepository.getById(id);
		entity.getLocations().size();
		LocationEntity locationEntity = this.locationService.getEntityById(idLocation);
		entity.addLocation(locationEntity);
		IconEntity a = this.iconRepository.save(entity);
		System.out.println("addlocationService : " + a.getId() + "  " + a.getLocations());
		
		
	}

	@Override
	public void removeLocation(Long id, Long idLocation) {
		IconEntity entity = this.iconRepository.getById(id);
		entity.getLocations().size();
		LocationEntity locationEntity = this.locationService.getEntityById(idLocation);
		entity.removeLocation(locationEntity);
		this.iconRepository.save(entity);
		
		
	}

	@Override
	public void delete(Long id) {
		this.iconRepository.deleteById(id);
		
	}

	
}
