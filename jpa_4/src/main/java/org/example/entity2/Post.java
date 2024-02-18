package org.example.entity2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * A post many comments -> Unidirectional -> Owner -> Post
 * Multiple comments belongs to a post -> Unidirectional -> Ownership -> Comments
 *
 * Bidirectional -> combination of above two
 */
@Entity
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String postContent;

    @OneToMany
    private List<Comment> comments;
}
