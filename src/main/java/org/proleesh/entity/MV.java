package org.proleesh.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author sung-hyuklee
 */
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "song")
@EqualsAndHashCode
@Entity
public class MV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mvUrl;

    @OneToOne(mappedBy = "mv", cascade = CascadeType.ALL)
    private Song song;

    public MV(String mvUrl){
        this.mvUrl = mvUrl;
    }
}
