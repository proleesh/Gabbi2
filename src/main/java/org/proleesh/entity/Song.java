package org.proleesh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author sung-hyuklee
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mv_id", referencedColumnName = "id")
    private MV mv;
    public Song(String title, String artist, String fileName, MV mv) {
        this.title = title;
        this.artist = artist;
        this.fileName = fileName;
        this.mv = mv;
    }
}
