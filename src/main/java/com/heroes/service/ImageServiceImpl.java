package com.heroes.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.heroes.dto.HeroDTO;
import com.heroes.dto.ImageDTO;
import com.heroes.entity.Hero;
import com.heroes.entity.Image;
import com.heroes.repository.HeroRepository;
import com.heroes.repository.ImageRepository;

@Service(value = "imageService")
@Transactional
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	HeroRepository heroRepository;
	
	@Autowired
	HeroService heroService;

	/*
	 *Compresses image bytes before storing them in the db 
	 */
//	@Override
	public static byte[] compressBytes(byte[] data) {
		
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		
		byte[] buffer = new byte[1024];
		while(!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch(IOException exception) {
			
		}
		
		return outputStream.toByteArray();
	}
	
	/*
	 *Uncompress image bytes before storing them in the db 
	 */
//	@Override
	public static byte[] decompressBytes(byte[] data) {
		 
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		ByteArrayOutputStream outputStream  = new ByteArrayOutputStream(data.length);
		
		byte[] buffer = new byte[1024];
		
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch(IOException exception) {
			
		} catch(DataFormatException exception) {
			
		}
		return outputStream.toByteArray();	
		}

	@Override
	public ImageDTO postImageToHero(MultipartFile file, String heroId) throws IOException {
		
		Optional<Hero> optionalHero = heroRepository.findById(Integer.parseInt(heroId));
		Hero hero = Hero.setEntityFromOptional(optionalHero);
		
		Image image = new Image(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		hero.setProfilePicture(image);
		image.setHero(hero);
		Image postedImage = imageRepository.save(image);

		hero.setProfilePicture(postedImage);
		
		heroRepository.save(hero);		
		
		HeroDTO heroDTO = HeroDTO.setDTO(hero);

		ImageDTO imageDTO = ImageDTO.setDTO(postedImage);
		imageDTO.setPicByte(decompressBytes(postedImage.getPicByte()));
		imageDTO.setId(postedImage.getId());
		
		heroDTO.setProfilePicture(imageDTO);
		
//		heroService.updateHero(heroDTO.getId(), heroDTO);
		heroService.postImageToHero(postedImage, heroId);
		return imageDTO;
	}

	@Override
	public ImageDTO getImage(String imageId) {
		Integer imageIdInteger = Integer.parseInt(imageId);
		Optional<Image> retrievedImage = imageRepository.findById(imageIdInteger);
		
		ImageDTO imageDTO = ImageDTO.setDTOFromOptional(retrievedImage);
		imageDTO.setPicByte(decompressBytes(retrievedImage.get().getPicByte()));
		imageDTO.setId(retrievedImage.get().getId());
		
		return imageDTO;
	}

}
