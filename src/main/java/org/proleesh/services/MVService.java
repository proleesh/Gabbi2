package org.proleesh.services;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.MV;
import org.proleesh.repository.MVRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author sung-hyuklee
 */
@RequiredArgsConstructor
@Service
public class MVService {
    private final MVRepository mvRepository;
    private final MVStorageService mvStorageService;
    public List<MV> getAllMVs() {
        return mvRepository.findAll();
    }

    public Optional<MV> getMVById(Long id) {
        return mvRepository.findById(id);
    }

    public String storeMVFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            // 확장자 추출 (마지막 "." 이후의 문자열을 가져옴)
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // UUID를 사용하여 새로운 파일명 생성
        String fileName = UUID.randomUUID().toString() + extension; // UUID + 확장자
        Path uploadPath = Paths.get("uploads/mv");

        try{
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "/mv/" + fileName;
        }catch(IOException e){
            throw new RuntimeException("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }

    public MV createMV(MV mv, MultipartFile file) {
        String fileName = storeMVFile(file);
        mv.setMvUrl(fileName);
        return mvRepository.save(mv);
    }

    public void deleteMVById(Long id) {
        mvRepository.deleteById(id);
    }
}
