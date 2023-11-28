package ibmec.projetocloud.ap1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibmec.projetocloud.ap1.exception.PacienteException;
import ibmec.projetocloud.ap1.model.Medico;
import ibmec.projetocloud.ap1.model.Paciente;
import ibmec.projetocloud.ap1.service.MedicoService;
import ibmec.projetocloud.ap1.service.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico/{idMedico}/paciente")
@CrossOrigin
class resourceNameController {

    @Autowired
    PacienteService pacienteService;

    @Autowired MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(@PathVariable("idMedico") long idMedico) {
        try {
            Optional<Medico> opMedico = this.medicoService.getById(idMedico);

            if (opMedico.isPresent() == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }

            return new ResponseEntity<>(opMedico.get().getPacientes(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Paciente> getById(@PathVariable("id") long id) {
        Optional<Paciente> existingItemOptional = pacienteService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> create(@PathVariable("idMedico") long idMedico, @RequestBody Paciente item) throws PacienteException {
        Paciente savedItem = pacienteService.save(idMedico, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Paciente> update(@PathVariable("id") long id, @RequestBody Paciente item) throws PacienteException {
        return new ResponseEntity<>(pacienteService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws PacienteException {
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}