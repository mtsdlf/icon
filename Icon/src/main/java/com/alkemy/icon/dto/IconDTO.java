package com.alkemy.icon.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IconDTO {
	private Long id;
	@NotEmpty
	private String title;
	private String imageUrl;
	private String description;
	@Positive
	private Long height;
	@Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message="Formato de fecha inválido")
	private String buildingDate;
	private List<CityDTO> cities;

}
