package br.com.fiap.Portaria.repository;

import br.com.fiap.Portaria.entity.Portaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortariaRepository extends JpaRepository<Portaria, Long> {
}
