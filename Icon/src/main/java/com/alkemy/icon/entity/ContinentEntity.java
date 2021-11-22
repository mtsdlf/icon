package com.alkemy.icon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@Table(name = "continents")
@SQLDelete(sql= "UPDATE continents SET deleted = true WHERE id=?")
@Where(clause= "deleted=false")
public class ContinentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String title;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private boolean deleted = Boolean.FALSE;
	
}
