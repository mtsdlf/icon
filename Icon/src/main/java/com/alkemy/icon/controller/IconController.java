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
	public ResponseEntity<IconDTO> getDetailsById(@Valid @PathVariable Long id) {
		IconDTO icon = this.iconService.getDetailsById(id);
		return ResponseEntity.ok(icon);
	}
	
	@GetMapping
	public ResponseEntity<List<IconDTO>> getDetailsByFilters(
			@Valid @RequestParam(required = false) String name, 
			@Valid @RequestParam(required = false) String date,
			@RequestParam(required = false) Set<Long> cities,
			@Valid @RequestParam(required = false, defaultValue = "ASC") String order
	) {
		List<IconDTO> icons = this.iconService.getByFilters(name, date, cities, order); 
		return ResponseEntity.ok(icons);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<IconDTO> update (@Valid @PathVariable Long id, @RequestBody IconDTO icon) {
		IconDTO result = this.iconService.update(id, icon);;
		return ResponseEntity.ok(result);
	}
	 
	@PostMapping
	public ResponseEntity<IconDTO> save(@Valid @RequestBody IconDTO icon) {
		IconDTO result = this.iconService.save(icon);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
		this.iconService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
