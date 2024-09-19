package org.proleesh.controller;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.Song;
import org.proleesh.services.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

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
        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);

        Song savedSong = songService.saveSong(song, file, mvFile);
        return ResponseEntity.ok(savedSong);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id){
        songService.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }



}
