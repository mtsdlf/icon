package com.alkemy.icon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.icon.dto.ContinentDTO;
import com.alkemy.icon.service.ContinentService;

@RestController
@RequestMapping("continents")
public class ContinentController {
	
	@Autowired
	private ContinentService continentService;
	
	@GetMapping
	public ResponseEntity<List<ContinentDTO>> getAll() {
		List<ContinentDTO> continents = continentService.getAllContinents();
		return ResponseEntity.ok().body(continents);
	}
	
	@PostMapping
	public ResponseEntity<ContinentDTO> save(@RequestBody ContinentDTO continent) {
		ContinentDTO savedContinent = continentService.save(continent);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedContinent);
	}
	
	

}
