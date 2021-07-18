package com.heroes.service;

import java.util.List;

import com.heroes.dto.HeroDTO;

public interface HeroService {
	
	List<HeroDTO> getHeroes();
	
	HeroDTO getHeroById(String heroId);

	
	HeroDTO postHero(HeroDTO heroDTO);
	
	HeroDTO updateHero(Integer heroId, HeroDTO heroDTO);
	
	void deleteHero(Integer heroId);

}
