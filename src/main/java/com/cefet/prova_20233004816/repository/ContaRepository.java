package com.cefet.prova_20233004816.repository;

import com.cefet.prova_20233004816.model.Conta;
import com.cefet.prova_20233004816.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    boolean existsByNumero(String numero);
    List<Conta> findByPessoa(Pessoa pessoa);

}