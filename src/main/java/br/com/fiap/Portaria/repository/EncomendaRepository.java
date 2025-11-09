package br.com.fiap.Portaria.repository;

import br.com.fiap.Portaria.entity.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {
}
