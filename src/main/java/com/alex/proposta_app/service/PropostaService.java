package com.alex.proposta_app.service;
import com.alex.proposta_app.domain.dto.PropostaRequestDTO;
import com.alex.proposta_app.domain.dto.PropostaResponseDTO;
import com.alex.proposta_app.domain.mapper.PropostaMapper;
import com.alex.proposta_app.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private NotificacaoService notificacaoService;

    private String exchange;

    public PropostaService(PropostaRepository repository, NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
        var propostaEntity = PropostaMapper.INSTANCE.converteDtoToProposta(requestDTO);
        repository.save(propostaEntity);
        var propostaResponseDTO = PropostaMapper.INSTANCE.convertetEntityToDto(propostaEntity);
        notificacaoService.notificar(propostaResponseDTO, exchange);
        return propostaResponseDTO;
    }

    public List<PropostaResponseDTO> buscar() {
        return PropostaMapper.INSTANCE.converteEntityToListDto(repository.findAll());
    }
}
