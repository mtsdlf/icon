package com.alkemy.icon.entity;

import java.util.HashSet;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
	
	@NotBlank
	private String title;
	
	@Positive
	private Long population;
	
	@Positive
	@Column(name = "area_in_m2")
	private Long area; //m2
	
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

	public void addIcon(IconEntity icon) {
		this.icons.add(icon);
	}
	
	public void removeIcon(IconEntity icon) {
		this.icons.remove(icon);
	}
	
}
