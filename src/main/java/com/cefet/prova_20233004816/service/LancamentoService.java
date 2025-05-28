package com.cefet.prova_20233004816.service;


import com.cefet.prova_20233004816.dto.LancamentoDTO;
import com.cefet.prova_20233004816.model.Conta;
import com.cefet.prova_20233004816.model.Lancamento;
import com.cefet.prova_20233004816.repository.ContaRepository;
import com.cefet.prova_20233004816.repository.LancamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepo;

    @Autowired
    private ContaRepository contaRepo;

    public List<LancamentoDTO> ListarTodos() {
        return lancamentoRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public LancamentoDTO buscarPorId(Long id) {
        Lancamento lancamento = lancamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado com ID: " + id));
        return toDTO(lancamento);
    }

    @Transactional
    public LancamentoDTO salvar(LancamentoDTO dto) {
        Conta conta = findContaById(dto.getIdConta());

        Lancamento lancamento = new Lancamento();
        lancamento.setValor(dto.getValor());
        lancamento.setConta(conta);


        double novoSaldo = conta.getSaldo() + dto.getValor();
        conta.setSaldo(novoSaldo);
        contaRepo.save(conta);

        Lancamento lancamentoSalvo = lancamentoRepo.save(lancamento);
        return toDTO(lancamentoSalvo);
    }

    @Transactional
    public LancamentoDTO atualizar(Long id, LancamentoDTO dto) {
        Lancamento lancamento = lancamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado com ID: " + id));

        Conta contaAntiga = lancamento.getConta();
        Conta contaNova = findContaById(dto.getIdConta());
        double valorAntigo = lancamento.getValor();
        double valorNovo = dto.getValor();


        if (!contaAntiga.getId().equals(contaNova.getId())) {

            contaAntiga.setSaldo(contaAntiga.getSaldo() - valorAntigo);
            contaRepo.save(contaAntiga);

            contaNova.setSaldo(contaNova.getSaldo() + valorNovo);
            contaRepo.save(contaNova);
        } else {
            contaAntiga.setSaldo(contaAntiga.getSaldo() - valorAntigo + valorNovo);
            contaRepo.save(contaAntiga);
        }


        lancamento.setValor(valorNovo);
        lancamento.setConta(contaNova);

        Lancamento lancamentoAtualizado = lancamentoRepo.save(lancamento);
        return toDTO(lancamentoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        Lancamento lancamento = lancamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado com ID: " + id));

        Conta conta = lancamento.getConta();
        double valor = lancamento.getValor();


        conta.setSaldo(conta.getSaldo() - valor);
        contaRepo.save(conta);

        lancamentoRepo.delete(lancamento);
    }

    public List<LancamentoDTO> listarPorConta(Long contaId) {
        Conta conta = findContaById(contaId);
        return lancamentoRepo.findByConta(conta).stream().map(this::toDTO).collect(Collectors.toList());
    }


    private LancamentoDTO toDTO(Lancamento lancamento) {
        LancamentoDTO dto = new LancamentoDTO();
        dto.setId(lancamento.getId());
        dto.setValor(lancamento.getValor());
        if (lancamento.getConta() != null) {
            dto.setIdConta(lancamento.getConta().getId());
            dto.setNumeroConta(lancamento.getConta().getNumero());
        }
        return dto;
    }

    private Conta findContaById(Long contaId) {
        return contaRepo.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada com ID: " + contaId));
    }
}


