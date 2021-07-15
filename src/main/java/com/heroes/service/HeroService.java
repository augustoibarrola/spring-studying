package com.heroes.service;

import java.util.List;

import com.heroes.dto.HeroDTO;

public interface HeroService {
	
	List<HeroDTO> getHeroes();
	
	HeroDTO postHero(HeroDTO heroDTO);
	
	HeroDTO updateHero(Integer heroId, HeroDTO heroDTO);

}
