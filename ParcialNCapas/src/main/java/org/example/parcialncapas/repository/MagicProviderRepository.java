package org.example.parcialncapas.repository;

import org.example.parcialncapas.domain.entity.MagicProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MagicProviderRepository extends JpaRepository<MagicProvider, UUID> {
}
