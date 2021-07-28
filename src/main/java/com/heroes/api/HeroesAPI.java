package com.heroes.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heroes.dto.*;
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
	
	@GetMapping(value="/heroes/{heroId}")
	public ResponseEntity<HeroDTO> getHero(@PathVariable("heroId") String heroId){
		HeroDTO heroDTO = heroService.getHeroById(heroId);
		return new ResponseEntity<HeroDTO>(heroDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/heroes")
	public ResponseEntity<HeroDTO> postHero(@RequestBody HeroDTO heroDTO) throws IOException {
		HeroDTO postedHeroDTO = heroService.postHero(heroDTO);
		return new ResponseEntity<HeroDTO>(postedHeroDTO, HttpStatus.CREATED);
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
	public ResponseEntity<HeroDTO> updateHero(@PathVariable("id") String id, @RequestBody HeroDTO heroDTO) throws NumberFormatException, IOException{

		HeroDTO updatedHero = heroService.updateHero(Integer.parseInt(id), heroDTO);
//		System.out.println(heroDTO.getProfilePicture());
		
		return new ResponseEntity<HeroDTO>(updatedHero, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/hero/{id}")
	public ResponseEntity<String> deleteHero(@PathVariable String id){
		heroService.deleteHero(Integer.parseInt(id));
		String successMessage = "Hero Successfully Deleted";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

}
