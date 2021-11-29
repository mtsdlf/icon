package com.alkemy.icon.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContinentDTO {
	private Long id;
	@NotEmpty
	private String title;
	private String imageUrl;

}
