package com.alkemy.icon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.dto.LocationBasicDTO;
import com.alkemy.icon.dto.LocationDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.entity.LocationEntity;
import com.alkemy.icon.exceptions.ParamNotFound;
import com.alkemy.icon.mapper.LocationMapper;
import com.alkemy.icon.repository.LocationRepository;
import com.alkemy.icon.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	private LocationRepository locationRepository;
	
	private LocationMapper locationMapper;
	
	@Autowired
	public LocationServiceImpl(
			LocationRepository locationRepository,
			LocationMapper locationMapper
	) {
		this.locationRepository = locationRepository;
		this.locationMapper = locationMapper;
	}
	
	@Override
	public LocationDTO save(LocationDTO dto) {
		LocationEntity entity = locationMapper.locationDTO2Entity(dto);
		LocationEntity entitySaved = locationRepository.save(entity);
		LocationDTO result = locationMapper.locationEntity2DTO(entitySaved, true);
		return result;
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
		LocationDTO locationDTO = this.locationMapper.locationEntity2DTO(entity.get(), false);
		return locationDTO;
	}
	
	@Override
	public LocationEntity getEntityById(Long id) {
		Optional<LocationEntity> entity = Optional.of(locationRepository.getById(id));
		if (!entity.isPresent()) {
			throw new ParamNotFound("id location not valid");
		}
		LocationEntity result = entity.get();
		return result;
	}

	@Override
	public void delete(Long id) {
		this.locationRepository.deleteById(id);
		
	}

	@Override
	public LocationDTO update(Long id, LocationDTO icon) {
		Optional<LocationEntity> oldEntity = Optional.of(this.locationRepository.getById(id));
		if (!oldEntity.isPresent()) {
			throw new ParamNotFound("location id not valid");
		}
		LocationEntity newEntity = locationMapper.locationDTO2Entity(icon);
		newEntity.setId(oldEntity.get().getId());
		LocationEntity entitySaved = locationRepository.save(newEntity);
		LocationDTO result = locationMapper.locationEntity2DTO(entitySaved, false);
		return result;
	}

}