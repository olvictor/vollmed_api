package com.example.med.controller;

import com.example.med.DTO.DadosMedicosDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrarMedico(@RequestBody DadosMedicosDTO dados){
        System.out.println(dados);
    }
}
