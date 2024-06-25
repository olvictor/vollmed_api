package com.example.med.repository;

import com.example.med.DTO.DadosPacienteResponseDTO;
import com.example.med.model.Paciente;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable page);
    @Query("""
            select p.ativo
            from Paciente p
            WHERE
            p.id = :id
            """)
    boolean findAtivoById(Long id);
}
