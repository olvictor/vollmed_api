package com.example.med.DTO;

import com.example.med.model.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroConsultaDTO(
        @NotNull
        Long pacienteId,

        @NotNull
        Long medicoId,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
