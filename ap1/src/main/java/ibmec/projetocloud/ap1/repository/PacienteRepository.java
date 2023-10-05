package ibmec.projetocloud.ap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibmec.projetocloud.ap1.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}
