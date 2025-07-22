package com.alex.proposta_app.service;
import com.alex.proposta_app.domain.dto.PropostaRequestDTO;
import com.alex.proposta_app.domain.dto.PropostaResponseDTO;
import com.alex.proposta_app.domain.mapper.PropostaMapper;
import com.alex.proposta_app.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository repository;

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
        var proposta = PropostaMapper.INSTANCE.converteDtoToProposta(requestDTO);
        repository.save(proposta);
        return PropostaMapper.INSTANCE.convertetEntityToDto(proposta);
    }
}
