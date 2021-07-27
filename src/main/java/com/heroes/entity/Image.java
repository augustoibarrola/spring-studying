package com.heroes.entity;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.heroes.dto.ImageDTO;
import com.heroes.service.ImageServiceImpl;

@Entity
@Table(name="IMAGE")
public class Image {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="NAME")
	private String name;
	@Column(name="IMAGE_TYPE")
	private String type;
	@Column(name="PIC_BYTE", length=1000)
	private byte[] picByte;
	@OneToOne(mappedBy="profilePicture")
//	@OneToOne
	private Hero hero;
	
	public Image() {
		
	}
	
	public Image(String name, String type, byte[] picByte){
		this.name = name;
		this.type = type;
		this.picByte = picByte;
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
	
	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public static Image setImage(ImageDTO imageDTO) {
		Image image = new Image(imageDTO.getName(),imageDTO.getType(), ImageServiceImpl.compressBytes(imageDTO.getPicByte()));
		return image;
	}



}
