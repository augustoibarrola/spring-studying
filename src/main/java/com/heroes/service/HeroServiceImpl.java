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
import com.heroes.repository.ImageRepository;

@Service(value = "heroService")
@Transactional
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired
	private ImageRepository imageRepository;

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

	@Override
	public HeroDTO updateHero(Integer heroId, HeroDTO heroDTO) {
		Optional<Hero> optionalHero= heroRepository.findById(heroId);
//		optionalHero.orElseThrow()
		Hero hero = Hero.setEntityFromOptional(optionalHero);
		//	UPDATE HERO ENTITY WITH HERODTO PROEPERTIES 
		hero.setName(heroDTO.getName());
		hero.setAlias(heroDTO.getAlias());
		hero.setSuperpower(heroDTO.getSuperpower());
		hero.setWeakness(heroDTO.getWeakness());
		hero.setImages(heroDTO.getImageEntities());
		
		for(Image image : hero.getImages()){
			if(!imageRepository.existsById(image.getId()) || image.getId() == null){
				Image savedImage = imageRepository.save(image);
				image.setId(savedImage.getId());
			}
		}
		
		
		heroRepository.save(hero);
		
		return heroDTO;
		
	}
	
	@Override
	public void deleteHero(Integer heroId){
		heroRepository.deleteById(heroId);
	}

}
