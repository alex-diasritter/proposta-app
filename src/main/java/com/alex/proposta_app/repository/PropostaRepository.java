package com.alex.proposta_app.repository;

import com.alex.proposta_app.domain.entity.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    //query derivada.
    //o próprio spring data jpa cria a query para nós.
    List<Proposta> findAllByIntegradaIsFalse();
}
