package br.com.fiap.Portaria.repository;

import br.com.fiap.Portaria.entity.Apartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
}
