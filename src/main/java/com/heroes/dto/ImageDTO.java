package com.heroes.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.heroes.entity.Image;

public class ImageDTO {
	
	private Integer id;
	private String name;
	private String type;
	private byte[] picByte;
	
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
	
	public static Image setImage(ImageDTO imageDTO) {
		Image image = new Image(imageDTO.getName(),imageDTO.getType(), imageDTO.getPicByte());
		return image;
	}
	
	

}
