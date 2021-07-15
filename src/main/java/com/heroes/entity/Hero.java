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
import javax.persistence.Table;

import com.heroes.dto.HeroDTO;
import com.heroes.dto.ImageDTO;

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
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="HERO_ID")
	private List<Image> images;
	
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public List<ImageDTO> getImageDTOs(){
		List<ImageDTO> imageDTOS = new ArrayList<>();
		for(Image image: this.images) {
			ImageDTO imageDTO = Image.setDTO(image);
			imageDTOS.add(imageDTO);
		}
		return imageDTOS;
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
	
	public static HeroDTO setDTO(Hero hero) {
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setId(hero.getId());
		heroDTO.setName(hero.getName());
		heroDTO.setAlias(hero.getAlias());
		heroDTO.setSuperpower(hero.getSuperpower());
		heroDTO.setWeakness(hero.getWeakness());
		heroDTO.setImages(hero.getImageDTOs());
		
		return heroDTO;
	}
	
	public static Hero setEntityFromOptional(Optional<Hero> optionalHero) {
		Hero hero = new Hero();
		hero.setId(optionalHero.get().getId());
		hero.setName(optionalHero.get().getName());
		hero.setAlias(optionalHero.get().getAlias());
		hero.setSuperpower(optionalHero.get().getSuperpower());
		hero.setWeakness(optionalHero.get().getWeakness());
		hero.setImages(optionalHero.get().getImages());
		
		return hero;
	}
	
	

}
