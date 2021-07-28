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
			if(hero.getProfilePicture()!=null) {
				ImageDTO profilePicture = hero.getProfilePictureDTO(hero.getProfilePicture());
				heroDTO.setProfilePicture(profilePicture);
			}
			heroesDTO.add(heroDTO);
		}

		return heroesDTO;
	}
	
	@Override
	public HeroDTO getHeroById(String heroId) {
		
		Optional<Hero> optionalHero = heroRepository.findById(Integer.parseInt(heroId));
		Hero hero = Hero.setEntityFromOptional(optionalHero);
		HeroDTO heroDTO = HeroDTO.setDTO(hero);
		
		if(optionalHero.get().getProfilePicture() !=null) {
			
			Image heroProfilePicture = optionalHero.get().getProfilePicture();
			ImageDTO heroProfilePictureDTO = ImageDTO.setDTO(heroProfilePicture);
			heroProfilePictureDTO.setPicByte(ImageServiceImpl.decompressBytes(heroProfilePicture.getPicByte()));
			heroProfilePictureDTO.setId(heroProfilePicture.getId());
			
			heroDTO.setProfilePicture(heroProfilePictureDTO);
		}
		
				
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
		
//		System.out.println(optionalHero.get().getProfilePicture());
		if(optionalHero.get().getProfilePicture() == null && heroDTO.getProfilePicture() !=null) {
			System.out.println("THERE IS NO PROFILEPICTURE IN DB BUT A NEW ONE IS BEING UPLOADED");

			/*
			 * if there is no profile picture already 
			 * AND
			 * a new one is being uploaded 
			 * */
			ImageDTO heroProfilePictureDTO = heroDTO.getProfilePicture();
			Image heroProfilePicture = Image.setImage(heroProfilePictureDTO);
			hero.setProfilePicture(heroProfilePicture);
			
			heroDTO.setProfilePicture(heroProfilePictureDTO);
//			heroRepository.save(hero);
			
			
		} else if(optionalHero.get().getProfilePicture() == null && heroDTO.getProfilePicture() == null) {
			/*
			 * if there is no profile picture already 
			 * AND
			 * a new one has not been uploaded 
			 * */
			System.out.println("no profilepicture already stored and no new one submitted");
//			heroRepository.save(hero);

		} else if(optionalHero.get().getProfilePicture() != null && heroDTO.getProfilePicture() == null) {
			System.out.println("THERE IS A PROFILEPICTURE IN DB ALREADY AND A NEW ONE HAS NOT BEEN UPLOADED");
			/*
			 * if there is a profile picture already present 
			 * AND
			 * a new one has not been uploaded 
			 * */
			Image heroProfilePicture = optionalHero.get().getProfilePicture();
			hero.setProfilePicture(heroProfilePicture);
			
			ImageDTO heroProfilePictureDTO = ImageDTO.setDTO(heroProfilePicture);
			heroProfilePictureDTO.setPicByte(ImageServiceImpl.decompressBytes(heroProfilePicture.getPicByte()));
			heroProfilePictureDTO.setId(heroProfilePicture.getId());
			heroDTO.setProfilePicture(heroProfilePictureDTO);
//			heroRepository.save(hero);
						
		} else if(optionalHero.get().getProfilePicture() != null && heroDTO.getProfilePicture() != null) {
			System.out.println("THERE IS A PROFILEPICTURE IN DB ALREADY AND A NEW ONE IS BEING UPLOADED");
			/*
			 * if there is a profile picture already present 
			 * AND
			 * a new one is being uploaded 
			 * */
			Image heroProfilePicture = optionalHero.get().getProfilePicture();
			hero.setProfilePicture(heroProfilePicture);
			
			ImageDTO heroProfilePictureDTO = heroDTO.getProfilePicture();
			heroDTO.setProfilePicture(heroProfilePictureDTO);
//			ImageDTO heroProfilePictureDTO = heroDTO.getProfilePicture();
//			
//			
//			System.out.println(heroDTO.getProfilePicture());
//
//			
//			Image heroProfilePicture = Image.setImage(heroProfilePictureDTO);  
//			hero.setProfilePicture(heroProfilePicture);
//			
//			Hero savedHero = heroRepository.save(hero);
//
//			
//			heroProfilePictureDTO.setPicByte(ImageServiceImpl.decompressBytes(savedHero.getProfilePicture().getPicByte()));
//			heroProfilePictureDTO.setId(savedHero.getProfilePicture().getId());
//			heroDTO.setProfilePicture(heroProfilePictureDTO);
//						
		} 

		heroRepository.save(hero);

		return heroDTO;
		
	}
	
	@Override
	public void deleteHero(Integer heroId){
		Optional<Hero> optionalHero = heroRepository.findById(heroId);
		Hero hero = Hero.setEntityFromOptional(optionalHero);
		heroRepository.delete(hero);
//		heroRepository.deleteById(heroId);
	}

	@Override
	public void postImageToHero(Image image, String heroId) throws IOException {

		Optional<Hero> optionalHero = heroRepository.findById(Integer.parseInt(heroId));
		Hero hero = Hero.setEntityFromOptional(optionalHero);
//		System.out.println(image);
		hero.setProfilePicture(image);
		
		heroRepository.save(hero);
		
	}

}
