package org.proleesh.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MVStorageService {
    private final Path mvStorageLocation;

    public MVStorageService(@Value("${mv.upload-dir}") String uploadDir) {
        this.mvStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.mvStorageLocation);
        }catch(IOException e){
            throw new RuntimeException("해당 파일은 업로드할 수 없습니다." + e.getMessage());
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = file.getOriginalFilename();

        try{
            Path targetLocation = this.mvStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return fileName;
        }catch(IOException e){
            throw new RuntimeException("해당 비디오 파일 저장 불가 " + fileName + ". 다시 시도 해보세요. " + e.getMessage());
        }
    }
}
