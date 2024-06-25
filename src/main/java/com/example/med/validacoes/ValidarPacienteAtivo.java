package com.example.med.validacoes;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.repository.PacienteRepository;
import com.example.med.service.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class ValidarPacienteAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private PacienteRepository pacienteRepository;


    public void validar(DadosCadastroConsultaDTO dados){
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.pacienteId());

        if(!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluido");
        }

    }
}
