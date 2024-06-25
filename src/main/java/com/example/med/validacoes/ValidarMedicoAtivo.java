package com.example.med.validacoes;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.repository.MedicoRepository;
import com.example.med.service.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidarMedicoAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosCadastroConsultaDTO dados){
        if(dados.medicoId() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.medicoId());

        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído .");
        }
    }
}
