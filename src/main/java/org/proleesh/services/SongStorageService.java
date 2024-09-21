package org.proleesh.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author sung-hyuklee
 */
@Service
public class SongStorageService {
    private final Path songStorageLocation;

    public SongStorageService(@Value("${music.upload-dir}") String uploadDir) {
        this.songStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize();
        try {
            Files.createDirectories(this.songStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("해당 파일은 업로드할 수 없습니다." + e.getMessage());
        }
    }


    public String storeFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        try {
            Path targetLocation = this.songStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("해당 음악 파일 저장 불가 " + fileName + ". 다시 시도 해보세요. " + e.getMessage());
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.songStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("파일 못찾음: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("파일 못찾음: " + fileName, e);
        }
    }
}
