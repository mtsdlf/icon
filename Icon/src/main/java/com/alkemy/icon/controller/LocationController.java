package com.alkemy.icon.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.dto.LocationBasicDTO;
import com.alkemy.icon.dto.LocationDTO;
import com.alkemy.icon.service.LocationService;

@RestController
@RequestMapping("locations")
public class LocationController {
	
	private LocationService locationService;
	
	@Autowired
	LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<LocationBasicDTO>> getAll() {
		List<LocationBasicDTO> locations = locationService.getAll();
		return ResponseEntity.ok(locations);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LocationDTO> getDetailsById(
			@Valid @PathVariable Long id) {
		LocationDTO location = this.locationService.getDetailsById(id);
		return ResponseEntity.ok(location);
	}	
	
	@GetMapping
	public ResponseEntity<List<LocationDTO>> getDetailsByFilters(
			@Valid @RequestParam(required = false) String name, 
			@Valid @RequestParam(required = false) Long continent,
			@Valid @RequestParam(required = false, defaultValue = "ASC") String order
	) {
		List<LocationDTO> locations = this.locationService.getByFilters(name, continent, order); 
		return ResponseEntity.ok(locations);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LocationDTO> update (
			@Valid @PathVariable Long id,
			@RequestBody LocationDTO location) {
		LocationDTO result = this.locationService.update(id, location);
		return ResponseEntity.ok(result);
	}	
	
	@PostMapping
	public ResponseEntity<LocationDTO> save(
			@Valid @RequestBody LocationDTO location) {
		LocationDTO result = locationService.save(location);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PostMapping("/{id}/icons/{idIcon}")
	public ResponseEntity<Void> addIcon(
			@Valid @PathVariable Long id, 
			@PathVariable Long idIcon){
		this.locationService.addIcon(id, idIcon);
		return ResponseEntity.status(HttpStatus.CREATED).build();	
	}

	@DeleteMapping("/{id}/icons/{idIcon}")
	public ResponseEntity<Void> delete(
			@Valid @PathVariable Long id, 
			@Valid @PathVariable Long idIcon) {
		this.locationService.removeIcon(id, idIcon);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@Valid @PathVariable Long id) {
		this.locationService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
