package com.alex.proposta_app.service;
import com.alex.proposta_app.domain.dto.PropostaResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDTO proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
