package br.com.casa.screenmatch.domain.repository;

import br.com.casa.screenmatch.domain.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
