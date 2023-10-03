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

import br.com.ibmec.cloud.demoapi.demoapi.model.Artista;
import br.com.ibmec.cloud.demoapi.demoapi.service.ArtistaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/artista")
@Tag(name = "Artista", description = "")
public class ArtistaController {

    @Autowired
    private ArtistaService _artistaService;

    @GetMapping
    public ResponseEntity<List<Artista>> getAll() {
        try {
            return new ResponseEntity<>(this._artistaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Artista> create(@Valid @RequestBody Artista item) {
        try {
            Artista result = this._artistaService.save(item);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Artista> getById(@PathVariable("id") long id) {

        Optional<Artista> result = this._artistaService.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } 
            
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("{id}")
    public ResponseEntity<Artista> update(@PathVariable("id") long id, @RequestBody Artista artistaNovosDados) {
        try {
            Artista result = this._artistaService.update(id, artistaNovosDados);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            this._artistaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}