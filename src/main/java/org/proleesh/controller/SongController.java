package org.proleesh.controller;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.Song;
import org.proleesh.entity.User;
import org.proleesh.repository.UserRepository;
import org.proleesh.services.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author sung-hyuklee
 */
@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;
    private final UserRepository userRepository;

    @GetMapping
    public List<Song> getAllSongs(){
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id){
        Optional<Song> song = songService.getSongById(id);
        return song.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/upload")
    public ResponseEntity<Song> uploadSong(
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("file")MultipartFile file,
            @RequestParam(value="mvFile", required=false) MultipartFile mvFile
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new RuntimeException("No authentication found");
        }

        String username = authentication.getName();
        System.out.println("Authenticated username: " + username); // 调试日志

        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("사용자를 못 찾았습니다."));

        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);
        song.setUser(user);

        Song savedSong = songService.saveSong(song, file, mvFile);
        return ResponseEntity.ok(savedSong);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id){
        songService.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }



}
