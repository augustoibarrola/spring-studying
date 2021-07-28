package com.heroes.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.heroes.dto.HeroDTO;
import com.heroes.dto.ImageDTO;
import com.heroes.service.ImageService;
import com.heroes.service.ImageServiceImpl;

@Entity
@Table(name="HERO")
public class Hero {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="NAME")
	private String name;
	@Column(name="ALIAS")
	private String alias;
	@Column(name="SUPERPOWER")
	private String superpower;
	@Column(name="WEAKNESS")
	private String weakness;
	@Column(name="DESCRIPTION")
	private String description;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PROFILE_PICTURE_ID", referencedColumnName="ID")
	private Image profilePicture;
	
	public Image getProfilePicture() {
		return this.profilePicture;
	}
	public void setProfilePicture(Image profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public ImageDTO getProfilePictureDTO(Image image){

			ImageDTO imageDTO = ImageDTO.setDTO(image);
			imageDTO.setPicByte(ImageServiceImpl.decompressBytes(image.getPicByte()));
			imageDTO.setId(image.getId());
		
			return imageDTO;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSuperpower() {
		return superpower;
	}
	public void setSuperpower(String superpower) {
		this.superpower = superpower;
	}
	public String getWeakness() {
		return weakness;
	}
	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static Hero setEntity(HeroDTO heroDTO) {
		Hero hero = new Hero();
		if(heroDTO.getId()!=null) {
			hero.setId(heroDTO.getId());			
		}
		hero.setName(heroDTO.getName());
		hero.setAlias(heroDTO.getAlias());
		hero.setSuperpower(heroDTO.getSuperpower());
		hero.setWeakness(heroDTO.getWeakness());
		hero.setDescription(heroDTO.getDescription());

		return hero;
	}
	
	public static Hero setEntityFromOptional(Optional<Hero> optionalHero) {
		Hero hero = new Hero();
		hero.setId(optionalHero.get().getId());
		hero.setName(optionalHero.get().getName());
		hero.setAlias(optionalHero.get().getAlias());
		hero.setSuperpower(optionalHero.get().getSuperpower());
		hero.setWeakness(optionalHero.get().getWeakness());	
		hero.setDescription(optionalHero.get().getDescription());
		
		return hero;
	}
	
	

}
