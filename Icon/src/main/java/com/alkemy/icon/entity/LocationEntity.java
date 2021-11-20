package com.alkemy.icon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "locations")
public class LocationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String title;
	
	private Long population;
	
	@Column(name = "area_in_m2")
	private Long area;
	
	private String icons;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "continent_id")
	private Long continentId;
}
