package com.heroes.dto;

import java.util.Optional;

import com.heroes.entity.Image;
import com.heroes.service.ImageServiceImpl;

public class ImageDTO {
	
	private Integer id;
	private String name;
	private String type;
	private byte[] picByte;
	private HeroDTO heroDTO;
	
	public ImageDTO(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	
	public HeroDTO getHeroDTO() {
		return heroDTO;
	}

	public void setHeroDTO(HeroDTO heroDTO) {
		this.heroDTO = heroDTO;
	}
	
	public static ImageDTO setDTO(Image image) {
		ImageDTO imageDTO = new ImageDTO(image.getName(), image.getType());
		return imageDTO;
	}
	
	public static ImageDTO setDTOFromOptional(Optional<Image> image) {
		ImageDTO imageDTO = new ImageDTO(image.get().getName(), image.get().getType());
		return imageDTO;
	}


}
