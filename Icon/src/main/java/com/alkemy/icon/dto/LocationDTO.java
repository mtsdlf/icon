package com.alkemy.icon.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
	private Long id;
	@NotEmpty
	private String title;
	private String imageUrl;
	@Positive
	private Long population;
	@Positive
	private Long area;
	private List<IconDTO> icons;
	private Long continentId;

}
