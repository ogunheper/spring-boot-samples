package com.sahabt.persistence.repositories;

import com.google.common.base.Optional;
import com.sahabt.persistence.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    Optional<AuthorEntity> findById(Integer id);
}
