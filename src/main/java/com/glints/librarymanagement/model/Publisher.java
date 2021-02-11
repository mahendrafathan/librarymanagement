package com.glints.librarymanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher extends Persistence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long publisher_id;

    @Column(name = "publisher_name")
    private String publisherName;

    public Publisher() {}

    public Publisher(String publisher_name) {
        this.publisherName = publisher_name;
    }

    public Long getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Long publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
