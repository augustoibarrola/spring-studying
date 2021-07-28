package com.heroes.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.heroes.entity.Hero;
import com.heroes.entity.Image;

public class HeroDTO {

	private Integer id;
	private String name;
	private String alias;
	private String superpower;
	private String weakness;
	private String description;
	private ImageDTO profilePicture;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ImageDTO getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(ImageDTO images) {
		this.profilePicture = images;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSuperpower() {
		return this.superpower;
	}

	public void setSuperpower(String superpower) {
		this.superpower = superpower;
	}

	public String getWeakness() {
		return this.weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	public static HeroDTO setDTO(Hero hero) {
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setId(hero.getId());
		heroDTO.setName(hero.getName());
		heroDTO.setAlias(hero.getAlias());
		heroDTO.setSuperpower(hero.getSuperpower());
		heroDTO.setWeakness(hero.getWeakness());
		heroDTO.setDescription(hero.getDescription());
		
		return heroDTO;
	}


}
