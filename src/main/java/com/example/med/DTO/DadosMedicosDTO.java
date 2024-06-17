package com.example.med.DTO;

import com.example.med.model.Especialidade;

public record DadosMedicosDTO(String nome, String email, String crm, Especialidade especialidade,DadosEndereco endereco) {
}
