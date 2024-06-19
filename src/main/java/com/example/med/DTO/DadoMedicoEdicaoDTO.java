package com.example.med.DTO;

import jakarta.validation.constraints.NotNull;

public record DadoMedicoEdicaoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
