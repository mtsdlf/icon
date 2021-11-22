package com.alkemy.icon.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.icon.dto.ContinentDTO;
import com.alkemy.icon.entity.ContinentEntity;

@Component
public class ContinentMapper {
	
	public ContinentEntity continentDTO2Entity(ContinentDTO dto) {
			ContinentEntity continentEntity = new ContinentEntity();
			continentEntity.setTitle(dto.getTitle());
			continentEntity.setImageUrl(dto.getImageUrl());
			return continentEntity;
	}

	public ContinentDTO continentEntity2DTO(ContinentEntity entity) {
		ContinentDTO dto = new ContinentDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImageUrl(entity.getImageUrl());
		return dto;
	}

	public List<ContinentDTO> continentEntityList2DTOList(List<ContinentEntity> entities) {
		List<ContinentDTO> dtos = new ArrayList<>();
		for (ContinentEntity entity : entities) {
			dtos.add(this.continentEntity2DTO(entity));
		}
		return dtos;
	}

}
