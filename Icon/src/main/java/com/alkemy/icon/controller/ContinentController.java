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

import com.alkemy.icon.dto.ContinentDTO;
import com.alkemy.icon.dto.IconDTO;
import com.alkemy.icon.service.ContinentService;

@RestController
@RequestMapping("continents")
public class ContinentController {
	
	private ContinentService continentService;
	
	@Autowired
	ContinentController(ContinentService continentService) {
		this.continentService = continentService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ContinentDTO>> getAll() {
		List<ContinentDTO> continents = continentService.getAll();
		return ResponseEntity.ok().body(continents);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContinentDTO> getDetailsById(@PathVariable Long id) {
		ContinentDTO continent = this.continentService.getDetailsById(id);
		return ResponseEntity.ok(continent);
	}
	
	@PostMapping
	public ResponseEntity<ContinentDTO> save(@RequestBody ContinentDTO continent) {
		ContinentDTO result = continentService.save(continent);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<ContinentDTO> update (@PathVariable Long id, @RequestBody ContinentDTO continent) {
		ContinentDTO result = this.continentService.update(id, continent);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.continentService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	

}
