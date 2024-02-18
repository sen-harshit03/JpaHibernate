package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "authors")
@NamedEntityGraphs({
                @NamedEntityGraph(
                        name = "Author.fetchBookListEager",
                        attributeNodes = @NamedAttributeNode(value = "bookList")
                ),
                @NamedEntityGraph(
                        name = "Author.fetchBookShopListEager",
                        subgraphs = @NamedSubgraph(
                                name = "bookList",
                                attributeNodes = @NamedAttributeNode("bookShopList")

                        )
                )
        // We can have as many graphs, but the name should vary
})

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    private Set<Book> bookList;
}
