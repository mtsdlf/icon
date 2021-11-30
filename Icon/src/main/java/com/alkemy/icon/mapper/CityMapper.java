package com.alkemy.icon.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alkemy.icon.dto.CityBasicDTO;
import com.alkemy.icon.dto.CityDTO;
import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.entity.CityEntity;

@Component
public class CityMapper {
	
	
	private IconMapper iconMapper;
	
	@Autowired
	public CityMapper(@Lazy IconMapper iconMapper) {
	
		this.iconMapper = iconMapper;
	}

	public void cityEntityRefreshValues(CityEntity entity, CityDTO dto) {
		entity.setTitle(dto.getTitle());
		entity.setPopulation(dto.getPopulation());
		entity.setArea(dto.getArea());
		entity.setContinentId(dto.getContinentId());
		entity.setImageUrl(dto.getImageUrl());
	}
	
	public CityEntity cityDTO2Entity(CityDTO dto) {
			CityEntity cityEntity = new CityEntity();
			cityEntity.setTitle(dto.getTitle());
			cityEntity.setPopulation(dto.getPopulation());
			cityEntity.setArea(dto.getArea());
			cityEntity.setImageUrl(dto.getImageUrl());
			cityEntity.setContinentId(dto.getContinentId());
			return cityEntity;
	}

	public CityDTO cityEntity2DTO(CityEntity entity, Boolean loadIcons) {
		CityDTO dto = new CityDTO();
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

	public List<CityDTO> citiesEntityList2DTOList(List<CityEntity> cityList, Boolean loadIcons) {
		List<CityDTO> dtos = new ArrayList<>();
		for (CityEntity entity : cityList) {
			dtos.add(this.cityEntity2DTO(entity, loadIcons));
		}
		return dtos;
	}
	
	/** 
	 * @param entities (Set or List)
	 * @param loadCities
	 */
	public List<CityDTO> cityEntitySet2DTOList(Collection<CityEntity> entities, boolean loadIcons) {
		List<CityDTO> dtos = new ArrayList<>();
		for (CityEntity entity : entities) {
			dtos.add(this.cityEntity2DTO(entity, loadIcons));
		}
		return dtos;
	}

	public List<CityBasicDTO> citiesEntityList2BasicDTOList(List<CityEntity> entities) {
		List<CityBasicDTO> dtos = new ArrayList<>();
		CityBasicDTO basicDTO;
		for (CityEntity entity : entities) {
			basicDTO = new CityBasicDTO();
			basicDTO.setId(entity.getId());
			basicDTO.setTitle(entity.getTitle());
			basicDTO.setImageUrl(entity.getImageUrl());
			dtos.add(basicDTO);
		}
		return dtos;
	}

}
