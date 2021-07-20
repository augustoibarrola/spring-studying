package com.heroes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.heroes.dto.HeroDTO;
import com.heroes.dto.ImageDTO;
import com.heroes.entity.Hero;
import com.heroes.entity.Image;
import com.heroes.repository.HeroRepository;
import com.heroes.repository.ImageRepository;

@Service(value = "heroService")
@Transactional
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ImageService imageService;

	@Override
	public List<HeroDTO> getHeroes() {

		Iterable<Hero> heroes = heroRepository.findAll();
		List<HeroDTO> heroesDTO = new ArrayList<>();
		for (Hero hero : heroes) {
			HeroDTO heroDTO = HeroDTO.setDTO(hero);
			heroesDTO.add(heroDTO);
		}

		return heroesDTO;
	}
	
	@Override
	public HeroDTO getHeroById(String heroId) {
		
		Optional<Hero> optionalHero = heroRepository.findById(Integer.parseInt(heroId));
		Hero hero = Hero.setEntityFromOptional(optionalHero);
		HeroDTO heroDTO = HeroDTO.setDTO(hero);
		
		if(optionalHero.get().getProfilePicture()!=null) {
			Optional<List<Image>> optionalImages = imageRepository.findAllByHero(hero);
			if(optionalImages !=null) {
				Optional<Image> image = imageRepository.findById(optionalImages.get().get(0).getId());
				ImageDTO imageDTO = ImageDTO.setDTOFromOptional(image);
				imageDTO.setPicByte(ImageServiceImpl.decompressBytes(image.get().getPicByte()));
				imageDTO.setId(image.get().getId());
				heroDTO.setProfilePicture(imageDTO);
			}
			System.out.println(heroDTO.getProfilePicture().getId());
		}
		System.out.println(heroDTO.getId());
		return heroDTO;
		
	}

	@Override
	public HeroDTO postHero(HeroDTO heroDTO){
		Hero hero = Hero.setEntity(heroDTO);
		
		Hero postedHero = heroRepository.save(hero);
		heroDTO.setId(postedHero.getId());		
		
		return heroDTO;
	}

	@Override
	public HeroDTO updateHero(Integer heroId, HeroDTO heroDTO) throws IOException {
		Optional<Hero> optionalHero= heroRepository.findById(heroId);
		Hero hero = Hero.setEntityFromOptional(optionalHero);
		hero.setName(heroDTO.getName());
		hero.setAlias(heroDTO.getAlias());
		hero.setSuperpower(heroDTO.getSuperpower());
		hero.setWeakness(heroDTO.getWeakness());
		hero.setDescription(heroDTO.getDescription());
		System.out.println(heroDTO.getProfilePicture());

		heroRepository.save(hero);

		return heroDTO;
		
	}
	
	@Override
	public void deleteHero(Integer heroId){
		heroRepository.deleteById(heroId);
	}

}
