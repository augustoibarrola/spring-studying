package com.heroes.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<List<HeroDTO>> getHeroes(){
		List<HeroDTO> heroes = heroService.getHeroes();
		return new ResponseEntity<List<HeroDTO>>(heroes, HttpStatus.OK);
	}
	/*
	 * http://localhost:3333/heroes-api/heroes
	 */
	 
	 @PostMapping(value="/hero")
	 public ResponseEntity<HeroDTO> postHero(@RequestBody HeroDTO heroDTO){
	 HeroDTO heroDTO = heroService.postHero(heroDTO);
	 return new RespondeEntity<HeroDTO>(heroDTO, HttpStatus.CREATED);
	 }
	

}
