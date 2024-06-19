package com.example.med.controller;

import com.example.med.DTO.DadosPacienteEdicaoDTO;
import com.example.med.DTO.DadosPacienteResponseDTO;
import com.example.med.DTO.DadosPacientesDTO;
import com.example.med.model.Paciente;
import com.example.med.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosPacientesDTO dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosPacienteResponseDTO> listarPaciente(@PageableDefault(size = 10, sort = ("nome")) Pageable page){
//        return repository.findAll(page).map(DadosPacienteResponseDTO::new);
        return repository.findAllByAtivoTrue(page).map(DadosPacienteResponseDTO::new);
    }

    @PutMapping()
    @Transactional
    public void editarPaciente(@RequestBody DadosPacienteEdicaoDTO dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.editarDadosPaciente(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.desativar();
    }
}
