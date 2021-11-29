package com.alkemy.icon.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.alkemy.icon.dto.IconBasicDTO;
import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.dto.CityDTO;
import com.alkemy.icon.entity.IconEntity;

@Component
public class IconMapper {
	private CityMapper cityMapper = new CityMapper();
	
	public void iconEntityRefreshValues(IconEntity entity, IconDTO dto) {
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setHeight(dto.getHeight());
		entity.setBuildingDate(
				this.stringToLocalDate(dto.getBuildingDate())
		);
	}
	
	public IconEntity iconDTO2Entity(IconDTO dto) {
			IconEntity entity = new IconEntity();
			entity.setTitle(dto.getTitle());
			entity.setDescription(dto.getDescription());
			entity.setHeight(dto.getHeight());
			entity.setBuildingDate(
					this.stringToLocalDate(dto.getBuildingDate())
					);
			entity.setImageUrl(dto.getImageUrl());
			return entity;
	}
	
	public IconDTO iconEntity2DTO(IconEntity entity, boolean loadCities) {
		IconDTO dto = new IconDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setHeight(entity.getHeight());
		dto.setBuildingDate(entity.getBuildingDate().toString());
		if (loadCities) {
			List<CityDTO> citiesDTOS = this.cityMapper.citiesEntityList2DTOList(entity.getCities(), false);
			dto.setCities(citiesDTOS);
		} 
		dto.setImageUrl(entity.getImageUrl());
		return dto;
	}
	
	public Set<IconEntity> iconDTOList2Entity(List<IconDTO> dtos) {
		Set<IconEntity> entities = new HashSet<>();
		for (IconDTO dto : dtos) {
			entities.add(this.iconDTO2Entity(dto));
		}
		
		return entities;
	}

	/** 
	 * @param entities (Set or List)
	 * @param loadCities
	 */
	public List<IconDTO> iconEntitySet2DTOList(Collection<IconEntity> entities, boolean loadCities) {
		List<IconDTO> dtos = new ArrayList<>();
		for (IconEntity entity : entities) {
			dtos.add(this.iconEntity2DTO(entity, false));
		}
		return dtos;
	}
	
	public List<IconBasicDTO> iconEntitySet2BasicDTOList(Collection<IconEntity> entities) {
		List<IconBasicDTO> dtos = new ArrayList<>();
		IconBasicDTO basicDTO;
		for (IconEntity entity : entities) {
			basicDTO = new IconBasicDTO();
			basicDTO.setId(entity.getId());
			basicDTO.setTitle(entity.getTitle());
			basicDTO.setImageUrl(entity.getImageUrl());
			dtos.add(basicDTO);
		}
		return dtos;
	}
	
	private LocalDate stringToLocalDate(String stringDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(stringDate, formatter);
		return date;
	}

}
