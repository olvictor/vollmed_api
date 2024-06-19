package com.example.med.DTO;

import com.example.med.model.Especialidade;
import com.example.med.model.Medico;

public record DadosMedicoResponseDTO(Long id,String nome, String email, String crm, Especialidade especialidade) {

    public DadosMedicoResponseDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
