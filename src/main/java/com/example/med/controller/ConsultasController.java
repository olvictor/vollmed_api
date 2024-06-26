package com.example.med.controller;

import com.example.med.DTO.DadosCadastroConsultaDTO;
import com.example.med.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarConsulta(@RequestBody @Valid DadosCadastroConsultaDTO dados){

       var dto = consultaService.agendar(dados);

        return ResponseEntity.ok(dto);
    }

}
