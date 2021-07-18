package com.heroes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heroes.entity.Image;
import com.heroes.entity.Hero;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	Optional<Image> findByName(String name);
	
	Optional<List<Image>> findAllByHero(Hero hero);
	
}
