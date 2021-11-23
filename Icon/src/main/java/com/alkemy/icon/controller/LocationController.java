package com.alkemy.icon.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<LocationDTO> getDetailsById(@PathVariable Long id) {
		LocationDTO location = this.locationService.getDetailsById(id);
		return ResponseEntity.ok(location);
	}
	
	@PostMapping
	public ResponseEntity<LocationDTO> save(@RequestBody LocationDTO location) {
		LocationDTO result = locationService.save(location);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/{id}")
		public ResponseEntity<LocationDTO> update (@PathVariable Long id, @RequestBody LocationDTO location) {
			LocationDTO result = this.locationService.update(id, location);
			return ResponseEntity.ok(result);
	}
		

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.locationService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
