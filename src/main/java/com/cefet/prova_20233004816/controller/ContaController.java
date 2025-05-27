package com.cefet.prova_20233004816.controller;

import com.cefet.prova_20233004816.dto.ContaDTO;
import com.cefet.prova_20233004816.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    // GET /contas
    @GetMapping
    public List<ContaDTO> listarTodas() {
        return contaService.listarTodos();
    }

    // GET /contas/{id}
    @GetMapping("/{id}")
    public ContaDTO buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }

    // POST /contas
    @PostMapping
    public ContaDTO criar(@RequestBody ContaDTO dto) {
        return contaService.salvar(dto);
    }

    // PUT /contas/{id}
    @PutMapping("/{id}")
    public ContaDTO atualizar(@PathVariable Long id, @RequestBody ContaDTO dto) {
        return contaService.atualizar(id, dto);
    }

    // DELETE /contas/{id}
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        contaService.deletar(id);
    }
}