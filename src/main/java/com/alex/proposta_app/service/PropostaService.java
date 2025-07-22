package com.alex.proposta_app.service;
import com.alex.proposta_app.domain.dto.PropostaRequestDTO;
import com.alex.proposta_app.domain.dto.PropostaResponseDTO;
import com.alex.proposta_app.domain.entity.Proposta;
import com.alex.proposta_app.domain.mapper.PropostaMapper;
import com.alex.proposta_app.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository repository;

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
        return PropostaMapper.INSTANCE.convertetEntityToDto(repository.save(
                PropostaMapper.INSTANCE.converteDtoToProposta(requestDTO)));
    }

    public List<PropostaResponseDTO> buscar() {
        return PropostaMapper.INSTANCE.converteEntityToListDto(repository.findAll());
    }
}
