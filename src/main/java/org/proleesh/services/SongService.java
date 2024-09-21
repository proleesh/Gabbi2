package org.proleesh.services;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.MV;
import org.proleesh.entity.Song;
import org.proleesh.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    private final MVService mvService;
    private final MVStorageService mvStorageService;

    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    public Optional<Song> getSongById(long id){
        return songRepository.findById(id);
    }



    public Song saveSong(Song song, MultipartFile file, MultipartFile mvFile){
        String fileName = songStorageService.storeFile(file);

        String uriString = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/songs/files/")
                .path(fileName)
                .toUriString();
        song.setAudioUrl(uriString);

        if (mvFile != null && !mvFile.isEmpty()) {
            String mvFileName = mvStorageService.storeFile(mvFile);
            song.setFileName(fileName);

            String mvUriString = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/songs/files/")
                    .path(mvFileName)
                    .toUriString();

            MV mv = new MV();
            mv.setMvUrl(mvUriString);
            mvService.createMV(mv, mvFile);
            song.setMv(mv);
        }

        return songRepository.save(song);
    }

    public void deleteSongById(long id){
        songRepository.deleteById(id);
    }
}
