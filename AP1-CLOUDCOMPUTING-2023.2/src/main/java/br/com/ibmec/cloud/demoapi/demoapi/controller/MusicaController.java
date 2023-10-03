package br.com.ibmec.cloud.demoapi.demoapi.controller;

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

import br.com.ibmec.cloud.demoapi.demoapi.model.Musica;
import br.com.ibmec.cloud.demoapi.demoapi.service.MusicaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/musica")
@Tag(name = "Musica", description = "")
class MusicaController {

    @Autowired
    MusicaService musicaService;

    @GetMapping
    public ResponseEntity<List<Musica>> getAll() {
        try {
            return new ResponseEntity<>(musicaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Musica> getById(@PathVariable("id") Long id) {
        Optional<Musica> existingItemOptional = musicaService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{idArtista}")
    public ResponseEntity<Musica> create(@PathVariable("idArtista") long idArtista, @RequestBody Musica musica) {
        try {
            Musica result = this.musicaService.create(idArtista, musica);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Musica> update(@PathVariable("id") Long id, @RequestBody Musica musica) {
        try {
            Musica result = this.musicaService.update(id,musica);    
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            this.musicaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
