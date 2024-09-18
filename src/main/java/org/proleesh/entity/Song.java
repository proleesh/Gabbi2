package org.proleesh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mv_id", referencedColumnName = "id")
    private MV mv;
    public Song(String title, String artist, MV mv, String fileName) {
        this.title = title;
        this.artist = artist;
        this.mv = mv;
        this.fileName = fileName;
    }
}
