package ibmec.projetocloud.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibmec.projetocloud.ap1.exception.MedicoException;
import ibmec.projetocloud.ap1.model.Medico;
import ibmec.projetocloud.ap1.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico create(Medico medico) {
        return this.medicoRepository.save(medico);
    }

    public Optional<Medico> getById(long id) {
        return this.medicoRepository.findById(id);
    }

    public List<Medico> getAll() {
        return this.medicoRepository.findAll();
    }

    public void saveOrUpdate(Medico item) {
        this.medicoRepository.save(item);
    }

    public Medico update(long id, Medico newData) throws MedicoException{
        Optional<Medico>  opMedico = this.medicoRepository.findById(id);
        
        if (opMedico.isPresent() == false) {
            throw new MedicoException("Não encontrei o médico a ser atualizado.");
        }

        Medico medico = opMedico.get();
        medico.setNome(newData.getNome());
        medico.setCpf(newData.getCpf());
        medico.setEspecializacao(newData.getEspecializacao());
        medico.setCrm(newData.getCrm());
        medico.setTelefone(newData.getTelefone());

        this.medicoRepository.save(medico);
        
        return medico;
    }

    public void delete(long id) throws MedicoException{
        Optional<Medico>  opMedico = this.medicoRepository.findById(id);
        
        if (opMedico.isPresent() == false) {
            throw new MedicoException("Não encontrei o médico a ser atualizado.");
        }

        this.medicoRepository.delete(opMedico.get());
    }

}
