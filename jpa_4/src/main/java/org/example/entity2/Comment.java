package org.example.entity2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
