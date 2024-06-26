package com.example.med.controller;

import com.example.med.DTO.*;
import com.example.med.model.Paciente;
import com.example.med.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacientesController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosPacientesDTO dados, UriComponentsBuilder uribuilder){
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uribuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosPacienteDetalhamentoEdicaoDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosPacienteResponseDTO>> listarPaciente(@PageableDefault(size = 10, sort = ("nome")) Pageable page){
//        return repository.findAll(page).map(DadosPacienteResponseDTO::new);
        var dadosBuscados = repository.findAllByAtivoTrue(page).map(DadosPacienteResponseDTO::new);
        return ResponseEntity.ok(dadosBuscados);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarPaciente(@RequestBody DadosPacienteEdicaoDTO dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.editarDadosPaciente(dados);

        return ResponseEntity.ok(new DadosPacienteDetalhamentoEdicaoDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.desativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosPacienteDetalhamentoEdicaoDTO(paciente));
    }
}
