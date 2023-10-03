package br.com.ibmec.cloud.demoapi.demoapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ibmec.cloud.demoapi.demoapi.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
    
}
