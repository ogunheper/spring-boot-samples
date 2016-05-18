package com.sahabt.persistence.repositories;

import com.google.common.base.Optional;
import com.sahabt.persistence.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Optional<BookEntity> findById(Integer id);
}
