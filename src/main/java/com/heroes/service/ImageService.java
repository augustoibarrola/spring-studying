package com.heroes.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.heroes.dto.ImageDTO;
import com.heroes.entity.Image;

public interface ImageService {
	
	byte[] compressBytes(byte[] data);
	byte[] decompressBytes(byte[] data);
	Integer postImage(MultipartFile file) throws IOException;
	ImageDTO getImage(String imageId);
}
