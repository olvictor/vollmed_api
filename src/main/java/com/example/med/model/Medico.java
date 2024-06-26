package com.example.med.model;


import com.example.med.DTO.DadoMedicoEdicaoDTO;
import com.example.med.DTO.DadosMedicosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(DadosMedicosDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void editarDadosMedico(DadoMedicoEdicaoDTO dados){
            if(dados.nome() != null){
                this.nome = dados.nome();
            }
            if(dados.telefone() != null){
                this.telefone = dados.telefone();
            }
            if(dados.endereco() != null){
               endereco.editarDadosEndereco(dados.endereco());
            }
    }

    public void desativar() {
        this.ativo = false;
    }
}
