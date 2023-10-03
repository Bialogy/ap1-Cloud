package br.com.ibmec.cloud.demoapi.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ibmec.cloud.demoapi.demoapi.model.Artista;
import br.com.ibmec.cloud.demoapi.demoapi.repository.ArtistaRepository;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository _artistaRepository;

    public List<Artista> findAll() {
        return this._artistaRepository.findAll();
    }

    public Optional<Artista> findById(long id) {
        return this._artistaRepository.findById(id);
    }

    public Artista save(Artista artista) throws Exception {
        if (this._artistaRepository.countByNome(artista.getNome()) > 0) {
            throw new Exception("Este nome já existe na base de dados");
        }
        this._artistaRepository.save(artista);
        return artista;
    }

    public Artista update(long id, Artista newData) throws Exception {
        Optional<Artista> result = this._artistaRepository.findById(id);

        if (result.isPresent() == false) {
            throw new Exception("Não encontrei o artista a ser atualizado");
        }

        Artista artistaASerAtualizado = result.get();
        artistaASerAtualizado.setNome(newData.getNome());
        artistaASerAtualizado.setDataNascimento(newData.getDataNascimento());
        artistaASerAtualizado.setPaisOrigem(newData.getPaisOrigem());
        this._artistaRepository.save(artistaASerAtualizado);
        return artistaASerAtualizado;
    }

    public void delete(long id) throws Exception {
        Optional<Artista> artistaASerExcluida = this._artistaRepository.findById(id);
        // Não achei o artista a ser excluido
        if (artistaASerExcluida.isPresent() == false) {
            throw new Exception("Não encontrei o artista a ser atualizado");
        }
        this._artistaRepository.delete(artistaASerExcluida.get());
    }

    public void saveMusica(Artista artista) {
        this._artistaRepository.save(artista);
    }

}
