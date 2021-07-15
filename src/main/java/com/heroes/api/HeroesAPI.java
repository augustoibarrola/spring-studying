package com.heroes.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heroes.dto.HeroDTO;
import com.heroes.service.HeroService;

@CrossOrigin
@RestController
@RequestMapping(value = "/heroes-api")
@Validated
public class HeroesAPI {

	@Autowired
	HeroService heroService;

	@GetMapping(value = "/heroes")
	public ResponseEntity<List<HeroDTO>> getHeroes() {
		List<HeroDTO> heroes = heroService.getHeroes();
		return new ResponseEntity<List<HeroDTO>>(heroes, HttpStatus.OK);
	}
	/*
	 * http://localhost:3333/heroes-api/heroes
	 */

	@PostMapping(value = "/hero")
	public ResponseEntity<HeroDTO> postHero(@RequestBody HeroDTO heroDTO) {
		HeroDTO postedHeroDTO = heroService.postHero(heroDTO);
		return new ResponseEntity<HeroDTO>(heroDTO, HttpStatus.CREATED);
	}
	/*
	 * http://localhost:3333/heroes-api/hero
	 * 
	 * 	{ 
	 * 		"name": " ", 
	 * 		"alias": " ", 
	 * 		"superpower": " ",
	 * 		"weakness": " " 
	 * 	}
	 */
	
	@PutMapping(value="/hero/{id}")
	public ResponseEntity<HeroDTO> updateHero(@PathVariable String id, @RequestBody HeroDTO hero ){
		HeroDTO updatedHero = heroService.updateHero(Integer.parseInt(id), hero);
		return new ResponseEntity<HeroDTO>(updatedHero, HttpStatus.OK);
		
	}

}
