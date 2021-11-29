 package com.alkemy.icon.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alkemy.icon.dto.LocationFiltersDTO;
import com.alkemy.icon.entity.LocationEntity;

@Component
public class LocationSpecification {
	
	public Specification<LocationEntity> getByFilters(LocationFiltersDTO filtersDTO) {
		return (root, query, criteriaBuilder) -> {
		
			List<Predicate> predicates = new ArrayList<>();
			
			if (StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(
										root.get("title")),
										"%" + filtersDTO.getName().toLowerCase() + "%"
						) 
				);	
			}
			
			if (!Objects.isNull(filtersDTO.getContinent())) {
				predicates.add(
						criteriaBuilder.equal(root.get("continent"), filtersDTO.getContinent())
				);	
			}
			
			//remove duplicates
			query.distinct(true);
				
			//order resolver
			String orderByField = "id";
			query.orderBy(
					filtersDTO.isAsc() ?
							criteriaBuilder.asc(root.get(orderByField)) :
							criteriaBuilder.desc(root.get(orderByField))
			);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	};
}}
