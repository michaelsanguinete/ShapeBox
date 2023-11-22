package com.shapebox.demo.controller;

import com.shapebox.demo.dto.ProdutoRequest;
import com.shapebox.demo.dto.ProdutoResponse;
import com.shapebox.demo.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity criarProduto(@Valid @RequestBody ProdutoRequest produtoRequest){
        service.criarProduto(produtoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<List<ProdutoResponse>> buscarProdutosBySku(@Valid @PathVariable String sku){
        return ResponseEntity.ok(service.buscarProdutosBySku(sku));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoResponse> buscarProdutoById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(service.buscarProdutoById(id));
    }

    @PutMapping("/atualiza/{sku}/{quantidade}")
    public ResponseEntity atualizarQuantidadeEstoqueBySku(@Valid @PathVariable String sku, @PathVariable int quantidade){
        service.atualizarQuantidadeEstoqueBySku(quantidade, sku);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
