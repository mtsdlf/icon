package com.alkemy.icon.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.dto.LocationBasicDTO;
import com.alkemy.icon.dto.LocationDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.entity.LocationEntity;

@Component
public class LocationMapper {
	
	@Autowired
	private IconMapper iconMapper;
	
	public void locationEntityRefreshValues(LocationEntity entity, LocationDTO dto) {
		entity.setTitle(dto.getTitle());
		entity.setPopulation(dto.getPopulation());
		entity.setArea(dto.getArea());
		entity.setContinentId(dto.getContinentId());
		entity.setImageUrl(dto.getImageUrl());
	}
	
	public LocationEntity locationDTO2Entity(LocationDTO dto) {
			LocationEntity locationEntity = new LocationEntity();
			locationEntity.setTitle(dto.getTitle());
			locationEntity.setPopulation(dto.getPopulation());
			locationEntity.setArea(dto.getArea());
			locationEntity.setImageUrl(dto.getImageUrl());
			locationEntity.setContinentId(dto.getContinentId());
			return locationEntity;
	}

	public LocationDTO locationEntity2DTO(LocationEntity entity, Boolean loadIcons) {
		LocationDTO dto = new LocationDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setPopulation(entity.getPopulation());
		dto.setArea(entity.getArea());
		if(loadIcons) {
			List<IconDTO> iconDTOS = this.iconMapper.iconEntitySet2DTOList(entity.getIcons(), loadIcons);
			dto.setIcons(iconDTOS);
		}
		
		dto.setImageUrl(entity.getImageUrl());
		dto.setContinentId(entity.getContinentId());
		return dto;
	}

	public List<LocationDTO> locationEntityList2DTOList(List<LocationEntity> locationList, Boolean loadIcons) {
		List<LocationDTO> dtos = new ArrayList<>();
		for (LocationEntity entity : locationList) {
			dtos.add(this.locationEntity2DTO(entity, loadIcons));
		}
		return dtos;
	}
	
	/** 
	 * @param entities (Set or List)
	 * @param loadLocations
	 */
	public List<LocationDTO> locationEntitySet2DTOList(Collection<LocationEntity> entities, boolean loadIcons) {
		List<LocationDTO> dtos = new ArrayList<>();
		for (LocationEntity entity : entities) {
			dtos.add(this.locationEntity2DTO(entity, true));
		}
		return dtos;
	}

	public List<LocationBasicDTO> locationEntityList2BasicDTOList(List<LocationEntity> entities) {
		List<LocationBasicDTO> dtos = new ArrayList<>();
		LocationBasicDTO basicDTO;
		for (LocationEntity entity : entities) {
			basicDTO = new LocationBasicDTO();
			basicDTO.setId(entity.getId());
			basicDTO.setTitle(entity.getTitle());
			basicDTO.setImageUrl(entity.getImageUrl());
			dtos.add(basicDTO);
		}
		return dtos;
	}

}
