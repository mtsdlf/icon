package com.alkemy.icon.dto;

import java.util.List;
import java.util.Set;

import com.alkemy.icon.entity.IconEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
	private Long id;
	private String title;
	private Long population;
	private Long area;
	private List<IconDTO> icons;
	private String imageUrl;
	private Long continentId;

}
