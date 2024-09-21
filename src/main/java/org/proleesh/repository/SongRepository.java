package org.proleesh.repository;

import org.proleesh.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author sung-hyuklee
 */
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s LEFT JOIN FETCH s.mv")
    List<Song> findAll();

    @Query("SELECT s FROM Song s LEFT JOIN FETCH s.mv WHERE s.id = :id")
    Optional<Song> findById(@Param("id") Long id);

}
