package org.proleesh.services;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.Song;
import org.proleesh.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author sung-hyuklee
 */
@RequiredArgsConstructor
@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongStorageService songStorageService;

    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    public Optional<Song> getSongById(long id){
        return songRepository.findById(id);
    }

    public Song saveSong(Song song, MultipartFile file){
        String fileName = songStorageService.storeFile(file);
        song.setFileName(fileName);
        return songRepository.save(song);
    }

    public void deleteSongById(long id){
        songRepository.deleteById(id);
    }
}
