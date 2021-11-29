package com.alkemy.icon.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "icons")
@SQLDelete(sql= "UPDATE icons SET deleted = true WHERE id=?")
@Where(clause= "deleted=false")
public class IconEntity {		

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String title;
	
	private String description;
	
	@Column(name = "height_in_m")
	private Long height;
	
	@Column(name = "building_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate buildingDate;
	
	@ManyToMany(mappedBy = "icons", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<LocationEntity> locations = new ArrayList<>();
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private boolean deleted = Boolean.FALSE;

}
