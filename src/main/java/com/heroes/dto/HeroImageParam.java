package com.heroes.dto;

import org.springframework.web.multipart.MultipartFile;

public class HeroImageParam {
	
	private HeroDTO heroDTO;
	private MultipartFile image;
	
	public HeroDTO getHeroDTO() {
		return heroDTO;
	}
	public void setHeroDTO(HeroDTO heroDTO) {
		this.heroDTO = heroDTO;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
