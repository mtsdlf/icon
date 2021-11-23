package com.alkemy.icon.repository.specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alkemy.icon.dto.IconFiltersDTO;
import com.alkemy.icon.entity.IconEntity;
import com.alkemy.icon.entity.LocationEntity;
import com.jayway.jsonpath.Predicate;

@Component
public class IconSpecification {
	
	public Specification<IconEntity> getByFilters(IconFiltersDTO filtersDTO) {
		return (root, query, criteriaBuilder) -> {
		
			List<Predicate> predicates = new ArrayList<>();
			
			if (StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						(Predicate) criteriaBuilder.like(
								criteriaBuilder.lower(
										root.get("title")),
										"%" + filtersDTO.getName().toLowerCase() + "%"
						) 
				);
				
			}
			
			if (StringUtils.hasLength(filtersDTO.getDate())) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(filtersDTO.getDate(), formatter);
				
				predicates.add(
						(Predicate) criteriaBuilder.equal(root.get("buildingDate"), date)
				);
			}
			
			if (!CollectionUtils.isEmpty(filtersDTO.getCities())) {
				Join<LocationEntity, IconEntity> join = root.join("locations", JoinType.INNER);
				Expression<String> citiesId = join.get("id");
				predicates.add((Predicate) citiesId.in(filtersDTO.getCities()));
			}

			query.distinct(true);
			
			String orderByField = "title";
			query.orderBy(
					filtersDTO.isAsc() ?
							criteriaBuilder.asc(root.get(orderByField)) :
							criteriaBuilder.desc(root.get(orderByField))
			);
			
			return criteriaBuilder.and((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[0]));
	};
}}
