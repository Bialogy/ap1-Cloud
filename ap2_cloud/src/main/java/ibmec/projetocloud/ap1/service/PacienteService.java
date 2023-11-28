package ibmec.projetocloud.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibmec.projetocloud.ap1.exception.PacienteException;
import ibmec.projetocloud.ap1.model.Medico;
import ibmec.projetocloud.ap1.model.Paciente;
import ibmec.projetocloud.ap1.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository repository;

    @Autowired 
    MedicoService medicoService;

    public List<Paciente> findAll() {
        return this.repository.findAll();
    }

    public Optional<Paciente> findById(long id) {
        return this.repository.findById(id);
    }

    public Paciente update(long id, Paciente newData) throws PacienteException {
        Optional<Paciente> opPaciente = this.repository.findById(id);

        if (opPaciente.isPresent() == false) {
            throw new PacienteException("Pacienteário não encontrado");
        }

        Paciente paciente = opPaciente.get();

        paciente.setNomePaciente(newData.getNomePaciente());
        paciente.setCpf(newData.getCpf());
        paciente.setDtNascimento(newData.getDtNascimento());
        paciente.setTelefone(newData.getTelefone());
        paciente.setEmail(newData.getEmail());

        this.repository.save(paciente);

        return paciente;
    }
    
    public Paciente save(long idMedico, Paciente item) throws PacienteException {
        Optional<Medico> opMedico = this.medicoService.getById(idMedico);

        if (opMedico.isPresent() == false) {
            throw new PacienteException("Medico não encontrado");
        }

        Medico medico = opMedico.get();
        item.setMedico(medico);
        this.repository.save(item);
       
        return item;
    }

    public void delete(long id) throws PacienteException {
        Optional<Paciente> opMedico = this.repository.findById(id);

        if (opMedico.isPresent() == false) {
            throw new PacienteException("Não encontrei o medico a ser atualizado");
        }

        this.repository.delete(opMedico.get());
    }
    
}
