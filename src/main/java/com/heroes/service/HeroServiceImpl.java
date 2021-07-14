package com.heroes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heroes.dto.HeroDTO;
import com.heroes.entity.Hero;
import com.heroes.repository.HeroRepository;

@Service(value = "heroService")
@Transactional
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepository;

	@Override
	public List<HeroDTO> getHeroes() {

//		Optional<List<Hero>> observableHeroes = heroRepository.getAllHeroes();
//		observableHeroes.orElseThrow(() -> new HeroException())
		Iterable<Hero> heroes = heroRepository.findAll();
		List<HeroDTO> heroesDTO = new ArrayList<>();
		for (Hero hero : heroes) {
			HeroDTO heroDTO = Hero.setDTO(hero);
			heroesDTO.add(heroDTO);
		}

		return heroesDTO;
	}

	@Override
	public HeroDTO postHero(HeroDTO heroDTO) {
		Hero hero = HeroDTO.setEntity(heroDTO);
		Hero savedHero = heroRepository.save(hero);
		heroDTO.setId(savedHero.getId());
		return heroDTO;
	}

}
