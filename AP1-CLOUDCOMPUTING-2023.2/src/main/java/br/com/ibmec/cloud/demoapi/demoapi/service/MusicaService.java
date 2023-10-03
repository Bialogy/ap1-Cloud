package br.com.ibmec.cloud.demoapi.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ibmec.cloud.demoapi.demoapi.model.Musica;
import br.com.ibmec.cloud.demoapi.demoapi.model.Artista;
import br.com.ibmec.cloud.demoapi.demoapi.repository.MusicaRepository;

@Service
public class MusicaService {
    @Autowired
    MusicaRepository musicaRepository;

    @Autowired
    ArtistaService artistaService;

    public List<Musica> findAll() {
        return this.musicaRepository.findAll();
    }

    public Optional<Musica> findById(long id) {
        return this.musicaRepository.findById(id);
    }

    public Musica create(long idMusica, Musica newMusica) throws Exception {
        Optional<Artista> opMusica = this.artistaService.findById(idMusica);

        if (opMusica.isPresent() == false) {
            throw new Exception("Não encontrei o artista para adicionar a música");
        }

        Artista artista = opMusica.get();
        artista.addMusica(newMusica);
        this.artistaService.saveMusica(artista);

        Musica result = artista.getMusica().get(artista.getMusica().size() - 1);
        return result;
    }

    public Musica update(long id, Musica newData) throws Exception {
        Optional<Musica> existingItemOptional = musicaRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new Exception("Não encontrei a musica a ser atualizada");

        Musica existingItem = existingItemOptional.get();

        existingItem.setNomeMusica(newData.getNomeMusica());
        existingItem.setAlbum(newData.getAlbum());
        existingItem.setAnoLancamento(newData.getAnoLancamento());
        existingItem.setGenero(newData.getGenero());
        existingItem.setLetra(newData.getLetra());

        musicaRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws Exception {
        Optional<Musica> musica = this.musicaRepository.findById(id);

        if (musica.isPresent() == false)
            throw new Exception("Não encontrei a música a ser atualizada");

        this.musicaRepository.delete(musica.get());
    }

}
