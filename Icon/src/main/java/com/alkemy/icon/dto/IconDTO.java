package com.alkemy.icon.dto;

import java.util.HashSet;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IconDTO {
	private Long id;
	private String title;
	private String description;
	private Long height;
	private String buildingDate;
	private List<LocationDTO> locations;
	private String imageUrl;

}
