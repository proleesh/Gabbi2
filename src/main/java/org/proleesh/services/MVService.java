package org.proleesh.services;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.MV;
import org.proleesh.repository.MVRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
        String fileName = file.getOriginalFilename();
        return "/mv/" + fileName;
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
