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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibmec.projetocloud.ap1.exception.MedicoException;
import ibmec.projetocloud.ap1.model.Medico;
import ibmec.projetocloud.ap1.service.MedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
@CrossOrigin
class MedicoController {

    @Autowired
    MedicoService medicoService;

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

    @GetMapping("{id}")
    public ResponseEntity<Medico> getById(@PathVariable("id") long id) {
        Optional<Medico> existingItemOptional = medicoService.getById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody Medico item) throws MedicoException {
        Medico savedItem = medicoService.create(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Medico> update(@PathVariable("id") long id, @RequestBody Medico item) throws MedicoException {
        return new ResponseEntity<>(medicoService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws MedicoException {
        medicoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}