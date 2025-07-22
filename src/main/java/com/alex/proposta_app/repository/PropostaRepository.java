package com.alex.proposta_app.repository;

import com.alex.proposta_app.domain.entity.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {}
