package com.alex.proposta_app.controller;

import com.alex.proposta_app.domain.dto.PropostaRequestDTO;
import com.alex.proposta_app.domain.dto.PropostaResponseDTO;
import com.alex.proposta_app.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PropostaController {

    @Autowired
    private PropostaService service;

    @PostMapping("/proposta")
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO) {
        var response = service.criar(requestDTO);
        return ResponseEntity.ok(response);
    }
}
