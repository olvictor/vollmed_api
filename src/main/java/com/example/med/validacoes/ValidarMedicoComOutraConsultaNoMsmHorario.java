package com.example.med.validacoes;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.repository.ConsultaRepository;
import com.example.med.service.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class ValidarMedicoComOutraConsultaNoMsmHorario implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCadastroConsultaDTO dados){
        var medicoPossuiOutraConsultaMsmHorario = consultaRepository.existsByMedicoIdAndData(dados.medicoId(),dados.data());

        if(medicoPossuiOutraConsultaMsmHorario){
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
