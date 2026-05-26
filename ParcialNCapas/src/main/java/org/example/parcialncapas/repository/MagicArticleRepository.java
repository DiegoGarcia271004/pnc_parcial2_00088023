package org.example.parcialncapas.repository;

import org.example.parcialncapas.domain.entity.MagicArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MagicArticleRepository extends JpaRepository<MagicArticle, UUID> {
    Boolean existsByNameIgnoreCase(String name);
}
