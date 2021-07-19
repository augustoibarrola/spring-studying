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
	public HeroDTO getHeroById(String heroId) {
		
		Optional<Hero> optionalHero = heroRepository.findById(Integer.parseInt(heroId));
		Hero hero = Hero.setEntityFromOptional(optionalHero);
		
		//set image IDs if present in DB
		
		
		//set hero 
		
		HeroDTO heroDTO = Hero.setDTO(hero);
		List<ImageDTO> testImages = new ArrayList<>();
		if (optionalHero.get().getImages()!=null) {
		testImages.add(imageService.getImage(Integer.toString(optionalHero.get().getImages().get(0).getId())));		
		heroDTO.setImages(testImages);
		}
		
		return heroDTO;
		
	}

	@Override
	public HeroDTO postHero(HeroDTO heroDTO, MultipartFile imageFile) throws IOException {
		Hero hero = HeroDTO.setEntity(heroDTO);
		
		Hero savedHero = heroRepository.save(hero);
		heroDTO.setId(savedHero.getId());
		Integer postedImageId = imageService.postImageToHero(imageFile, Integer.toString(heroDTO.getId()) );
		HeroDTO returnedSavedHero = Hero.setDTO(savedHero);
		
		
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
		
		//images below are COMPRESSED from db
		//need to iterate over images from db
//		List<Image> compressedImagesFromDB = hero.getImages();
//		List<ImageDTO> decompressedImagesToFE = new ArrayList<>();
//		compressedImagesFromDB.forEach(imageFromDB -> {
//			for(ImageDTO imageDTO:heroDTO.getImages()) {
//				if(imageFromDB.getId().equals(imageDTO.getId())) {
//					imageDTO.setPicByte(ImageServiceImpl.decompressBytes(imageFromDB.getPicByte()));
//				}
//			}
//		});
		imageService.postImageToHeroTest(heroDTO.getImages().get(0), hero.getId());
		heroRepository.save(hero);
		if(hero.getImages()!=null) {
			
		List<ImageDTO> decompressedImages = new ArrayList<>();
		hero.getImages().forEach(image -> {
			//decompress images
			ImageDTO imageDTO = new ImageDTO(image.getName(), image.getType());
			imageDTO.setPicByte(ImageServiceImpl.decompressBytes(image.getPicByte()));
			imageDTO.setId(image.getId());
			decompressedImages.add(imageDTO);
//			image.setPicByte(ImageServiceImpl.decompressBytes(image.getBytes()));
			//set them to heroDTO's images
		});
		heroDTO.setImages(decompressedImages);
		}
		
		return heroDTO;
		
	}
	
	@Override
	public void deleteHero(Integer heroId){
		heroRepository.deleteById(heroId);
	}

}
