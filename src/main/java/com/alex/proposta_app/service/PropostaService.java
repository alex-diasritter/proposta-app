package com.alex.proposta_app.service;
import com.alex.proposta_app.domain.dto.PropostaRequestDTO;
import com.alex.proposta_app.domain.dto.PropostaResponseDTO;
import com.alex.proposta_app.domain.entity.Proposta;
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
        repository.save(propostaEntity); //salva com integrada true
        notificarRabbitMQ(propostaEntity); //caso não consiga mandar pro rabbit salva com integrada false
        return PropostaMapper.INSTANCE.convertetEntityToDto(propostaEntity);
    }

    /**
     * No Spring Data JPA, o metodo save() tem um comportamento de "upsert" (uma combinação de INSERT e UPDATE).
     * Se o objeto não tem um identificador (ID nulo): A entidade é considerada nova, e a operação executada é um INSERT.
     * Se o objeto já tem um identificador: A entidade é considerada existente. O framework tentará mesclar
     * (merge) as alterações do objeto em memória com o registro correspondente no banco de dados, resultando
     * em uma instrução UPDATE.
     */
    private void notificarRabbitMQ(Proposta proposta) {
        try {
            //fluxo normal, rabbit no ar
            notificacaoService.notificar(proposta, exchange);
        } catch (RuntimeException ex) {
            //rabbit caiu. proposta não integrada. futuramente um job para tentar enviar novamente as propostas.
            proposta.setIntegrada(false);
            repository.save(proposta);
        }
    }

    public List<PropostaResponseDTO> buscar() {
        return PropostaMapper.INSTANCE.converteEntityToListDto(repository.findAll());
    }
}
