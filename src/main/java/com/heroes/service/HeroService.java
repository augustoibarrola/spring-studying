package com.heroes.service;

import java.util.List;

import com.heroes.dto.HeroDTO;

public interface HeroService {
	
	List<HeroDTO> getHeroes();

}