package com.example.med.DTO;

import com.example.med.model.Paciente;

public record DadosPacienteResponseDTO(Long id, String nome, String email, String cpf) {

    public  DadosPacienteResponseDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(),paciente.getEmail(),paciente.getCpf());
    }
}
