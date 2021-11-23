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
	private String Date;
	private Set<Long> cities;
	private String order =  "asc";
	
	public boolean isAsc() {
		return this.order.compareToIgnoreCase("ASC") == 0;
	} 
		
	public boolean isDesc() {
		return this.order.compareToIgnoreCase("DESC") == 0;
	}
	
}
