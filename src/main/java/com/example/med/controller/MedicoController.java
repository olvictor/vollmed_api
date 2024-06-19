package com.example.med.controller;

import com.example.med.DTO.DadoMedicoEdicaoDTO;
import com.example.med.DTO.DadosMedicoResponseDTO;
import com.example.med.DTO.DadosMedicosDTO;
import com.example.med.model.Medico;
import com.example.med.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<DadosMedicoResponseDTO> listarMedico(@PageableDefault(size = 10, sort = ("nome")) Pageable paginacao){
//       return repository.findAll(paginacao).map(DadosMedicoResponseDTO::new);
         return repository.findAllByAtivoTrue(paginacao).map(DadosMedicoResponseDTO::new);
    }

    @PutMapping
    @Transactional
    public  void editarMedico(@RequestBody @Valid DadoMedicoEdicaoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.editarDadosMedico(dados);

    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluirMedico(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    // EXCLUSAO LOGICA //
    @DeleteMapping("/{id}")
    @Transactional
    public  void exclusaoLogicaDeMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativar();
    }

}