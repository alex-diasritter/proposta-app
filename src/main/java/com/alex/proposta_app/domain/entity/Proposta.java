package com.alex.proposta_app.domain.entity;

import jakarta.persistence.*;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovado;
    private boolean integrado;
    private String obs;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
