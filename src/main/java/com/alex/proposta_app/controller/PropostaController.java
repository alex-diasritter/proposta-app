package com.alex.proposta_app.controller;
import com.alex.proposta_app.domain.dto.PropostaRequestDTO;
import com.alex.proposta_app.domain.dto.PropostaResponseDTO;
import com.alex.proposta_app.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaService service;

    @PostMapping()
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO) {
        System.out.println("post acionado");
        var response = service.criar(requestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(response.getId())
                .toUri()).body(response);
    }

    @GetMapping()
    public  ResponseEntity<List<PropostaResponseDTO>> buscar(){
        System.out.println("get acionado");
        return ResponseEntity.ok(service.buscar());
    }

}
