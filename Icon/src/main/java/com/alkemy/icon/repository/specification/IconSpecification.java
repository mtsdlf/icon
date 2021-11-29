 package com.alkemy.icon.repository.specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alkemy.icon.dto.IconFiltersDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.entity.CityEntity;

@Component
public class IconSpecification {
	
	public Specification<IconEntity> getByFilters(IconFiltersDTO filtersDTO) {
		return (root, query, criteriaBuilder) -> {
		
			List<Predicate> predicates = new ArrayList<>();
			
			if (StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("title")),
										"%" + filtersDTO.getName().toLowerCase() + "%"
						) 
				);	
			}
			
			if (StringUtils.hasLength(filtersDTO.getDate())) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(filtersDTO.getDate(), formatter);
				predicates.add(
						criteriaBuilder.equal(
								root.<LocalDate>get("buildingDate"), 
								date
						)
				);
			}
			
			if (!CollectionUtils.isEmpty(filtersDTO.getCities())) {
				Join<CityEntity, IconEntity> join = root.join("locations", JoinType.INNER);
				Expression<String> citiesId = join.get("id");
				predicates.add(citiesId.in(filtersDTO.getCities()));
			}
			
			//remove duplicates
			query.distinct(true);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	};
}}
