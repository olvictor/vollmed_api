package com.example.med.validacoes;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.service.ValidacaoException;

import java.time.DayOfWeek;
import org.springframework.stereotype.Component;


@Component

public class ValidadadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void  validar(DadosCadastroConsultaDTO dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesDaAbertura = dataConsulta.getHour() < 7 ;
        var aposFechamento = dataConsulta.getHour() > 18 ;

        if(domingo || aposFechamento || antesDaAbertura){
            throw  new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

    }
}
