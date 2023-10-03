package br.com.ibmec.cloud.demoapi.demoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ibmec.cloud.demoapi.demoapi.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    int countByNome(String nome);
}
