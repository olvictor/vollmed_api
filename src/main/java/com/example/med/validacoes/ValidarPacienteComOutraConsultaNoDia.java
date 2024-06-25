package com.example.med.validacoes;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.repository.ConsultaRepository;
import com.example.med.service.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidarPacienteComOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCadastroConsultaDTO dados){
        var primerioHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.pacienteId(), primerioHorario,ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente ja possui uma consulta marcada nesse dia .");
        }
    }
}
