package org.proleesh.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "commented_user_id", nullable = false)
    private User commentedUser;

    private String content;

    public Comment(User user, User commentedUser, String content){
        this.user = user;
        this.commentedUser = commentedUser;
        this.content = content;
    }
}
