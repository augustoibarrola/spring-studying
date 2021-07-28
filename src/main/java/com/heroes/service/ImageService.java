package com.heroes.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.heroes.dto.ImageDTO;
import com.heroes.entity.Image;

public interface ImageService {
	
//	byte[] compressBytes(byte[] data);
//	byte[] decompressBytes(byte[] data);
	ImageDTO postImageToHero(MultipartFile file, String heroId) throws IOException;
//	public Image postImageToHeroTest(ImageDTO imageDTO, Integer heroId) throws IOException;

	ImageDTO getImage(String imageId);

}
