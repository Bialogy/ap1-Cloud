package br.com.ibmec.cloud.demoapi.demoapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "musica")
public class Musica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 200, nullable = false)
    private String nomeMusica;

    @Column(length = 200, nullable = false)
    private String album;

    @Column(length = 200, nullable = false)
    private String anoLancamento;

    @Column(length = 100, nullable = false)
    private String letra;
    
    @Column(length = 100, nullable = false)
    private String genero;

    public String getLetra() {
        return letra;
    }

    public void setLetra(String cep) {
        this.letra = letra;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
