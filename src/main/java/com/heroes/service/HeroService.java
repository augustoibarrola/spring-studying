package com.heroes.service;

import java.io.IOException;
import java.util.List;

import com.heroes.dto.HeroDTO;

public interface HeroService {
	
	List<HeroDTO> getHeroes();
	
	HeroDTO getHeroById(String heroId);

	
	HeroDTO postHero(HeroDTO heroDTO);
	
	HeroDTO updateHero(Integer heroId, HeroDTO heroDTO) throws IOException;
	
	void deleteHero(Integer heroId);

}
