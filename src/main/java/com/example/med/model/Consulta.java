package com.example.med.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")

    private Paciente paciente;

    private LocalDateTime data;
}
