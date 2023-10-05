package ibmec.projetocloud.ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibmec.projetocloud.ap1.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}

