package ibmec.projetocloud.ap1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibmec.projetocloud.ap1.model.Medico;
import ibmec.projetocloud.ap1.service.MedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> getAll() {
        try {
            List<Medico> items = new ArrayList<Medico>();

            medicoService.getAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Medico> create(@Valid @RequestBody Medico item) {
        try {
            Medico result = this.medicoService.create(item);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Medico> getById(@PathVariable("id") long id) {

        Optional<Medico> result = this.medicoService.getById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } 
            
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("{id}")
    public ResponseEntity<Medico> update(@PathVariable("id") long id, @Valid @RequestBody Medico medicoNovosDados) {
        try {
            Medico result = this.medicoService.update(id, medicoNovosDados);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            this.medicoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}