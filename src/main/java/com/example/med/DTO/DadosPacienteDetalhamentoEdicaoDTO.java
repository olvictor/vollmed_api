package com.example.med.DTO;

import com.example.med.model.Endereco;
import com.example.med.model.Paciente;

public record DadosPacienteDetalhamentoEdicaoDTO (Long id, String nome, String telefone,String cpf, Endereco endereco){
    public DadosPacienteDetalhamentoEdicaoDTO(Paciente paciente){
        this(paciente.getId(),paciente.getNome(),paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
