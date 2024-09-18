package org.proleesh.services;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.MV;
import org.proleesh.repository.MVRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MVService {
    private final MVRepository mvRepository;

    public List<MV> getAllMVs() {
        return mvRepository.findAll();
    }

    public Optional<MV> getMVById(Long id) {
        return mvRepository.findById(id);
    }

    public MV createMV(MV mv) {
        return mvRepository.save(mv);
    }

    public void deleteMVById(Long id) {
        mvRepository.deleteById(id);
    }
}
