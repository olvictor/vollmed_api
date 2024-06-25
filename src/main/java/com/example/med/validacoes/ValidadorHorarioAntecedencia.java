package com.example.med.validacoes;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.service.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;


@Component

public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void  validar(DadosCadastroConsultaDTO dados){
        var dataConsulta = dados.data();
        var dataAtual = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(dataAtual,dataConsulta).toMinutes();
        System.out.println(diferencaEmMinutos);
        if( diferencaEmMinutos < 30){
            throw  new ValidacaoException("Consulta deve ser agendada com antecendencia minima de 30 minutos.");
        }
    }
}
