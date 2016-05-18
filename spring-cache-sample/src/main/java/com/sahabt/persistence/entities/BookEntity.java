package com.sahabt.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String isbn;
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false, name = "author_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_book_author"))
    private AuthorEntity author;
}
