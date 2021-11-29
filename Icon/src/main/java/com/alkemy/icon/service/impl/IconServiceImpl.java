package com.alkemy.icon.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icon.dto.IconBasicDTO;
import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.dto.IconFiltersDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.exceptions.ParamNotFound;
import com.alkemy.icon.mapper.IconMapper;
import com.alkemy.icon.repository.IconRepository;
import com.alkemy.icon.repository.specification.IconSpecification;
import com.alkemy.icon.service.IconService;

@Service
public class IconServiceImpl implements IconService{
	
	private IconRepository iconRepository;
	
	private IconSpecification iconSpecification;
	
	private IconMapper iconMapper;

	@Autowired
	public IconServiceImpl(
			IconRepository iconRepository, 
			IconSpecification iconSpecification, 
			IconMapper iconMapper
	) {
		this.iconRepository = iconRepository;
		this.iconSpecification = iconSpecification;
		this.iconMapper = iconMapper;
	}
		
	@Override
	public List<IconBasicDTO> getAll() {
		List<IconEntity> entities = this.iconRepository.findAll();
		List<IconBasicDTO> iconBasicDTOS = this.iconMapper.iconEntitySet2BasicDTOList(entities);
		return iconBasicDTOS;
	}
	
	@Override
	public IconEntity getEntityById(Long id) {
		Optional<IconEntity> entity = iconRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("id icon not valid");
		}
		IconEntity result = entity.get();
		return result;
	}

	@Override
	public IconDTO getDetailsById(Long id) {
		Optional<IconEntity> entity = this.iconRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("icon id not valid");
		}
		IconDTO iconDTO = this.iconMapper.iconEntity2DTO(entity.get(), true);
		return iconDTO;
	}
	
	@Override public List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order) { 
		IconFiltersDTO filtersDTO = new IconFiltersDTO(name, date, cities, order);
		List<IconEntity> entities = this.iconRepository.findAll(this.iconSpecification.getByFilters(filtersDTO));
		List<IconDTO> dtos = this.iconMapper.iconEntitySet2DTOList(entities, true);
		return dtos;
	}
	
	@Override
	public IconDTO update(Long id, IconDTO icon) {
		//TODO: More Exceptions
		Optional<IconEntity> entity = this.iconRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("icon id not valid");
		}
		
		this.iconMapper.iconEntityRefreshValues(entity.get(), icon);
		IconEntity updatedEntity= this.iconRepository.save(entity.get());
		IconDTO result = iconMapper.iconEntity2DTO(updatedEntity, false);
		return result;
	} 
	
	@Override
	public IconDTO save(IconDTO iconDTO) {
		IconEntity entity = iconMapper.iconDTO2Entity(iconDTO);
		IconEntity entitySaved = iconRepository.save(entity);
		IconDTO result = iconMapper.iconEntity2DTO(entitySaved, true);
		return result;
	}

	@Override
	public void delete(Long id) {
		Optional<IconEntity> entity = this.iconRepository.findById(id);
		if (entity.isPresent()) {
			throw new ParamNotFound("icon id not valid");
		}
		this.iconRepository.deleteById(id);
	}

}
