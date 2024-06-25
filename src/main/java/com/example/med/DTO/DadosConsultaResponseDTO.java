package com.example.med.DTO;

import com.example.med.model.Consulta;

import java.time.LocalDateTime;

public record DadosConsultaResponseDTO(Long id, Long medicoId, Long pacienteId, LocalDateTime data) {
    public DadosConsultaResponseDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getData());
    }
}
