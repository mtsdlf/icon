package com.alkemy.icon.controller;

import java.util.List;

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

import com.alkemy.icon.dto.CityBasicDTO;
import com.alkemy.icon.dto.CityDTO;
import com.alkemy.icon.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {
	
	private CityService cityService;
	
	@Autowired
	CityController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CityBasicDTO>> getAll() {
		List<CityBasicDTO> locations = cityService.getAll();
		return ResponseEntity.ok(locations);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CityDTO> getDetailsById(
			@Valid @PathVariable Long id) {
		CityDTO location = this.cityService.getDetailsById(id);
		return ResponseEntity.ok(location);
	}	
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> getDetailsByFilters(
			@Valid @RequestParam(required = false) String name, 
			@Valid @RequestParam(required = false) Long continent,
			@Valid @RequestParam(required = false, defaultValue = "ASC") String order
	) {
		List<CityDTO> locations = this.cityService.getByFilters(name, continent, order); 
		return ResponseEntity.ok(locations);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CityDTO> update (
			@Valid @PathVariable Long id,
			@RequestBody CityDTO location) {
		CityDTO result = this.cityService.update(id, location);
		return ResponseEntity.ok(result);
	}	
	
	@PostMapping
	public ResponseEntity<CityDTO> save(
			@Valid @RequestBody CityDTO location) {
		CityDTO result = cityService.save(location);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PostMapping("/{id}/icons/{idIcon}")
	public ResponseEntity<Void> addIcon(
			@Valid @PathVariable Long id, 
			@PathVariable Long idIcon){
		this.cityService.addIcon(id, idIcon);
		return ResponseEntity.status(HttpStatus.CREATED).build();	
	}

	@DeleteMapping("/{id}/icons/{idIcon}")
	public ResponseEntity<Void> delete(
			@Valid @PathVariable Long id, 
			@Valid @PathVariable Long idIcon) {
		this.cityService.removeIcon(id, idIcon);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@Valid @PathVariable Long id) {
		this.cityService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
