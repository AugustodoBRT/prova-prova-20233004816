package com.cefet.prova_20233004816.controller;

import com.cefet.prova_20233004816.dto.ContaDTO;
import com.cefet.prova_20233004816.dto.PessoaDTO;
import com.cefet.prova_20233004816.model.Conta;
import com.cefet.prova_20233004816.service.ContaService;
import com.cefet.prova_20233004816.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @Autowired
    private ContaService contaService;

    @GetMapping("/{id}/contas")
    public List<ContaDTO> listarContasPorPessoa(@PathVariable Long id) {
        return contaService.listarPorPessoa(id);
    }

    @GetMapping
    public List<PessoaDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public PessoaDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public PessoaDTO criar(@RequestBody PessoaDTO dto) {
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public PessoaDTO atualizar(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}