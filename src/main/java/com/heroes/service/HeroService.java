package com.heroes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.heroes.dto.HeroDTO;
import com.heroes.dto.ImageDTO;
import com.heroes.entity.Image;

public interface HeroService {
	
	List<HeroDTO> getHeroes();
	
	HeroDTO getHeroById(String heroId);

		
	HeroDTO updateHero(Integer heroId, HeroDTO heroDTO) throws IOException;

	void postImageToHero(Image image, String heroId) throws IOException;
	
	void deleteHero(Integer heroId);

//	HeroDTO postHero(HeroDTO heroDTO, MultipartFile imageFile) throws IOException;

	HeroDTO postHero(HeroDTO heroDTO);

}
