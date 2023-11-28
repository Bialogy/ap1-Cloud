package ibmec.projetocloud.ap1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pacientes")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    @NotBlank(message = "O campo nome do paciente não pode estar vazio.")
    private String nomePaciente;

    @Column(nullable = false)
    @NotBlank(message = "O campo cpf não pode estar vazio.")
    private String cpf;

    @Column(nullable = false)
    @NotBlank(message = "O campo data de nascimento não pode estar vazio.")
    private String dtNascimento;

    @Column(nullable = false)
    @NotBlank(message = "O campo telefone não pode estar vazio.")
    private String telefone;

    @Column(nullable = false)
    @NotBlank(message = "O campo email não pode estar vazio.")
    private String email;

    @ManyToOne
    @JsonIgnore
    private Medico medico;

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

}
