package com.example.med.controller;

import com.example.med.DTO.DadosMedicosDTO;
import com.example.med.model.Medico;
import com.example.med.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosMedicosDTO dados){
        repository.save(new Medico(dados));
    }
}
