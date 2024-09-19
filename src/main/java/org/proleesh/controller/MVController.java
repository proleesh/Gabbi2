package org.proleesh.controller;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.MV;
import org.proleesh.services.MVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mvs")
@RequiredArgsConstructor
public class MVController {
    private final MVService mvService;

    @GetMapping
    public List<MV> getAllMVs(){
        return mvService.getAllMVs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MV> getMVById(@PathVariable Long id){
        Optional<MV> mv = mvService.getMVById(id);
        return mv.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/upload")
    public ResponseEntity<MV> uploadMV(
            @RequestParam("mvTitle") String mvTitle,
            @RequestParam("file")MultipartFile file
            ){
        MV mv = new MV();
        mv.setMvUrl(mvTitle);

        MV savedMV = mvService.createMV(mv, file);
        return ResponseEntity.ok(savedMV);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMV(@PathVariable Long id){
        mvService.deleteMVById(id);
        return ResponseEntity.noContent().build();
    }
}
