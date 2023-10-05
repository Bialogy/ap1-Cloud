package ibmec.projetocloud.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibmec.projetocloud.ap1.model.Medico;
import ibmec.projetocloud.ap1.model.Paciente;
import ibmec.projetocloud.ap1.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository repository;

    @Autowired 
    MedicoService postService;

    public List<Paciente> findAll() {
        return this.repository.findAll();
    }

    public Optional<Paciente> findById(long id) {
        return this.repository.findById(id);
    }

    public Paciente update(long id, Paciente newData) throws Exception {
        Optional<Paciente> opPaciente = this.repository.findById(id);

        if (opPaciente.isPresent() == false) {
            throw new Exception("Commentário não encontrado");
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
    
    public Paciente save(long idPost, Paciente item) throws Exception {
        Optional<Medico> opPost = this.postService.getById(idPost);

        if (opPost.isPresent() == false) {
            throw new Exception("Post não encontrado");
        }

        Medico medico = opPost.get();
        item.setMedico(medico);
        this.repository.save(item);
       
        return item;
    }

    public void delete(long id) throws Exception {
        Optional<Paciente> opPost = this.repository.findById(id);

        if (opPost.isPresent() == false) {
            throw new Exception("Não encontrei o post a ser atualizado");
        }

        this.repository.delete(opPost.get());
    }
    
}
