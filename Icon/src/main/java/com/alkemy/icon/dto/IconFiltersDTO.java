package com.alkemy.icon.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IconFiltersDTO {
	private String name;
	private String date;
	private Set<Long> cities;
	
}
