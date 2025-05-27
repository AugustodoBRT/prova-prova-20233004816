package com.cefet.prova_20233004816.service;

import com.cefet.prova_20233004816.dto.PessoaDTO;
import com.cefet.prova_20233004816.model.Pessoa;
import com.cefet.prova_20233004816.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepo;

    public List<PessoaDTO> listarTodos() {
        return pessoaRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PessoaDTO buscarPorId(Long id) {
        return toDTO(pessoaRepo.findById(id).orElseThrow());
    }

    public PessoaDTO salvar(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        return toDTO(pessoaRepo.save(pessoa));
    }

    public PessoaDTO atualizar(Long id, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepo.findById(id).orElseThrow();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        return toDTO(pessoaRepo.save(pessoa));
    }

    public void deletar(Long id) {
        pessoaRepo.deleteById(id);
    }

    private PessoaDTO toDTO(Pessoa p) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setCpf(p.getCpf());
        return dto;
    }
}