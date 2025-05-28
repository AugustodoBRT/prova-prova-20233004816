package com.cefet.prova_20233004816.repository;

import com.cefet.prova_20233004816.model.Conta;
import com.cefet.prova_20233004816.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByConta(Conta conta);
}
