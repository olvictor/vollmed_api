package com.example.med.controller;

import com.example.med.DTO.DadosPacientesDTO;
import com.example.med.model.Paciente;
import com.example.med.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public void cadastrarPaciente(@RequestBody DadosPacientesDTO dados){
        repository.save(new Paciente(dados));
    }
}
