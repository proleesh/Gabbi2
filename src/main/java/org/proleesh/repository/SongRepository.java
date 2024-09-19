package org.proleesh.repository;

import org.proleesh.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sung-hyuklee
 */
public interface SongRepository extends JpaRepository<Song, Long> {
}
