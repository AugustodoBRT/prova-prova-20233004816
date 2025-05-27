package com.cefet.prova_20233004816.service;

import com.cefet.prova_20233004816.dto.ContaDTO;
import com.cefet.prova_20233004816.model.Conta;
import com.cefet.prova_20233004816.model.Pessoa;
import com.cefet.prova_20233004816.repository.ContaRepository;
import com.cefet.prova_20233004816.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepo;

    @Autowired
    private PessoaRepository pessoaRepo;

    public List<ContaDTO> listarTodos() {
        return contaRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ContaDTO buscarPorId(Long id) {
        Conta conta = contaRepo.findById(id).orElseThrow();
        return toDTO(conta);
    }

    public ContaDTO salvar(ContaDTO dto) {
        if (contaRepo.existsByNumero(dto.getNumero())) {
            throw new RuntimeException("Número de conta já existe.");
        }

        Pessoa pessoa = pessoaRepo.findAll().stream()
                .filter(p -> p.getCpf().equals(dto.getCpfPessoa()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada pelo CPF."));

        Conta conta = new Conta();
        conta.setNumero(dto.getNumero());
        conta.setPessoa(pessoa);

        return toDTO(contaRepo.save(conta));
    }

    public ContaDTO atualizar(Long id, ContaDTO dto) {
        Conta conta = contaRepo.findById(id).orElseThrow();

        Pessoa pessoa = pessoaRepo.findAll().stream()
                .filter(p -> p.getCpf().equals(dto.getCpfPessoa()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada pelo CPF."));

        conta.setNumero(dto.getNumero());
        conta.setPessoa(pessoa);

        return toDTO(contaRepo.save(conta));
    }

    public void deletar(Long id) {
        contaRepo.deleteById(id);
    }

    public List<ContaDTO> listarPorPessoa(Long pessoaId) {
        Pessoa pessoa = pessoaRepo.findById(pessoaId).orElseThrow();
        return contaRepo.findByPessoa(pessoa).stream().map(this::toDTO).collect(Collectors.toList());
    }



    private ContaDTO toDTO(Conta conta) {
        ContaDTO dto = new ContaDTO();
        dto.setId(conta.getId());
        dto.setNumero(conta.getNumero());
        dto.setNomePessoa(conta.getPessoa().getNome());
        dto.setCpfPessoa(conta.getPessoa().getCpf());
        return dto;
    }


}