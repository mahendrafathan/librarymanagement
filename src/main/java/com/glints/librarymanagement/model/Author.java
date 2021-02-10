package com.glints.librarymanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class Author extends Persistence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long author_id;

    @Column(name = "author_name")
    private String authorName;

    public Author() {}

    public Author(String author_name) {
        this.authorName = author_name;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
