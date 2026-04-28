package com.ipn.mx.frases.domain.repository;

import com.ipn.mx.frases.domain.entities.Frase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraseRepository extends JpaRepository<Frase, Long> {
}
