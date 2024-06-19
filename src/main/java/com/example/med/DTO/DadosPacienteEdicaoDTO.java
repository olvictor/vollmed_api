package com.example.med.DTO;

import com.example.med.model.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosPacienteEdicaoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
