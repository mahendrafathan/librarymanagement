package com.glints.librarymanagement.Repository;

import com.glints.librarymanagement.model.Author;
import com.glints.librarymanagement.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
    Publisher findByPublisherNameIgnoreCase(String name);
}
