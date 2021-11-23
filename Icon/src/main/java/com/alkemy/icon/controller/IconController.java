package com.alkemy.icon.controller;

import java.util.List;
import java.util.Set;

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

import com.alkemy.icon.dto.IconBasicDTO;
import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.service.IconService;

@RestController
@RequestMapping("icons")
public class IconController {
	
	private IconService iconService;
	
	@Autowired
	IconController(IconService iconService) {
		this.iconService = iconService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<IconBasicDTO>> getAll() {
		List<IconBasicDTO> icons = this.iconService.getAll();	
		return ResponseEntity.ok(icons);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IconDTO> getDetailsById(@PathVariable Long id) {
		IconDTO icon = this.iconService.getDetailsById(id);
		return ResponseEntity.ok(icon);
	}
	
	/*
	 * @GetMapping public ResponseEntity<List<IconDTO>> getDetailsByFilter(
	 * 
	 * @PathVariable(required = false) String name,
	 * 
	 * @PathVariable(required = false) String date,
	 * 
	 * @PathVariable(required = false) Set<Long> cities,
	 * 
	 * @PathVariable(required = false, value = "ASC") String order ) { List<IconDTO>
	 * icons = this.iconService.getByFilters(name, date, cities, order); return
	 * ResponseEntity.ok(icons); }
	 */
	
	@PostMapping
	public ResponseEntity<IconDTO> save(@RequestBody IconDTO icon) {
		IconDTO result = this.iconService.save(icon);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<IconDTO> update (@PathVariable Long id, @RequestBody IconDTO icon) {
		IconDTO result = this.iconService.update(id, icon);;
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.iconService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping("/{id}/locations/{idLocation}")
	public ResponseEntity<Void> addLocation(@PathVariable Long id, @PathVariable Long idLocation){
		this.iconService.addLocation(id, idLocation);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	
	@DeleteMapping("/{id}/locations/{idLocation}")
	public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long idLocation) {
		this.iconService.removeLocation(id, idLocation);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
