package com.example.med.DTO;

import com.example.med.model.Especialidade;
import com.example.med.model.Medico;

public record DadosMedicoResponseDTO(String nome, String email, String crm, Especialidade especialidade) {

    public DadosMedicoResponseDTO(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
