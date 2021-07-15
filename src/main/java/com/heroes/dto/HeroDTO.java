package com.heroes.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.heroes.entity.Hero;
import com.heroes.entity.Image;

public class HeroDTO {

	private Integer id;
	@NotNull
	private String name;
	private String alias;
	@NotNull
	private String superpower;
	private String weakness;
//	  description:string;
	private String description;
//	  images: Image[];
	private List<ImageDTO> images;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
	
	public List<Image> getImageEntities(){
		List<Image> images = new ArrayList<>();
		for(ImageDTO imageDTO: this.images) {
			Image image = ImageDTO.setImage(imageDTO);
			images.add(image);
		}
		return images;
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

	public static Hero setEntity(HeroDTO heroDTO) {
		Hero hero = new Hero();
		hero.setId(heroDTO.getId());
		hero.setName(heroDTO.getName());
		hero.setAlias(heroDTO.getAlias());
		hero.setSuperpower(heroDTO.getSuperpower());
		hero.setWeakness(heroDTO.getWeakness());
		hero.setImages(heroDTO.getImageEntities());

		return hero;
	}


}
