package com.heroes.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heroes.dto.ImageDTO;
import com.heroes.entity.Image;
import com.heroes.repository.ImageRepository;
import com.heroes.service.ImageService;

@CrossOrigin
@RestController
@RequestMapping(value="/image-api")
public class ImageAPI {
	
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	ImageService imageService;
	
	@GetMapping(value="/images/{imageId}")
	public ResponseEntity<ImageDTO> getImage(@PathVariable("imageId") String imageId) throws IOException{
		ImageDTO retrievedImage = imageService.getImage(imageId);
		return new ResponseEntity<ImageDTO>(retrievedImage, HttpStatus.OK);
	}
	
	@PostMapping(value = "/{heroId}/images")
	public ResponseEntity<ImageDTO> uploadImageToHero(@PathVariable("heroId") String heroId, @RequestParam("imageFile") MultipartFile file) throws IOException {
		
		ImageDTO postedImage = imageService.postImageToHero(file, heroId);
		return new ResponseEntity<ImageDTO> (postedImage, HttpStatus.CREATED);
	}
	
	
	

}
