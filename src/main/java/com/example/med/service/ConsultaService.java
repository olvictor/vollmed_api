package com.example.med.service;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.DTO.DadosConsultaResponseDTO;
import com.example.med.model.Consulta;
import com.example.med.model.Medico;
import com.example.med.repository.ConsultaRepository;
import com.example.med.repository.MedicoRepository;
import com.example.med.repository.PacienteRepository;
import com.example.med.validacoes.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosConsultaResponseDTO agendar(DadosCadastroConsultaDTO dados){
            if(!pacienteRepository.existsById(dados.pacienteId())){
                throw new ValidacaoException("Pacietne não encontrado no bando de dados.");
            }

            if(dados.medicoId() != null && !medicoRepository.existsById(dados.medicoId())){
                throw new ValidacaoException("Medico não encontrado no bando de dados.");
            }

            validadores.forEach(v->v.validar(dados));

            var paciente = pacienteRepository.getReferenceById(dados.pacienteId());
            var medico = buscarMedicoAleatoriamente(dados);

            if (medico == null){
                throw new ValidacaoException("Não existe médico disponível nessa data.");
            }

            var consulta = new Consulta(null, medico, paciente, dados.data());
            consultaRepository.save(consulta);

            return new DadosConsultaResponseDTO(consulta);
    }

    private Medico buscarMedicoAleatoriamente(DadosCadastroConsultaDTO dados) {
        if(dados.medicoId() != null){
            return medicoRepository.getReferenceById(dados.medicoId());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatoria quando um médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatoriamenLivreNaData(dados.especialidade(),dados.data());
    }
}
