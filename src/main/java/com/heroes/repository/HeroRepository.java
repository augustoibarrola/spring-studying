package com.heroes.repository;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.heroes.entity.Hero;

public interface HeroRepository extends CrudRepository<Hero, Integer> {


}
