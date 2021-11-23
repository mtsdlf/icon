package com.alkemy.icon.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.alkemy.icon.dto.IconDTO;

import lombok.Data;

@Entity
@Data
@Table(name = "locations")
@SQLDelete(sql= "UPDATE locations SET deleted = true WHERE id=?")
@Where(clause= "deleted=false")
public class LocationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String title;
	
	private Long population;
	
	@Column(name = "area_in_m2")
	private Long area; //m2
	
	//private String icons;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "continent_id", insertable = false, updatable = false)
	private ContinentEntity continent;
	
	@Column(name = "continent_id", nullable = false)
	private Long continentId;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private boolean deleted = Boolean.FALSE;
	
	@ManyToMany(
		cascade = {
				CascadeType.PERSIST,
				CascadeType.MERGE 
		}
	)
	
	@JoinTable(
			name = "icons_locations",
			joinColumns = @JoinColumn(name = "locations_id", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name = "icons_id", referencedColumnName="id")
			)
		private Set<IconEntity> icons = new HashSet<IconEntity>();

	
}
