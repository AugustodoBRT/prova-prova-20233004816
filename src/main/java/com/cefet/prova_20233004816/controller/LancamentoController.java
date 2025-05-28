package com.cefet.prova_20233004816.controller;

import com.cefet.prova_20233004816.dto.LancamentoDTO;
import com.cefet.prova_20233004816.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    // GET /lancamentos
    @GetMapping
    public List<LancamentoDTO> listarTodos() {
        return lancamentoService.listarTodos();
    }

    // GET /lancamentos/{id}
    @GetMapping("/{id}")
    public LancamentoDTO buscarPorId(@PathVariable Long id) {
        return lancamentoService.buscarPorId(id);
    }

    // POST /lancamentos
    @PostMapping
    public LancamentoDTO criar(@RequestBody LancamentoDTO dto) {
        return lancamentoService.salvar(dto);
    }

    // PUT /lancamentos/{id}
    @PutMapping("/{id}")
    public LancamentoDTO atualizar(@PathVariable Long id, @RequestBody LancamentoDTO dto) {
        return lancamentoService.atualizar(id, dto);
    }

    // DELETE /lancamentos/{id}
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        lancamentoService.deletar(id);
    }
}
