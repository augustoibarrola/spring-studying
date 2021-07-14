package com.heroes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heroes.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
	Optional<Image> findByName(String name);
}
