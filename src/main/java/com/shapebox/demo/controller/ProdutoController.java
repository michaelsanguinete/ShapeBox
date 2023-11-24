package com.shapebox.demo.controller;

import com.shapebox.demo.dto.ProdutoRequest;
import com.shapebox.demo.dto.ProdutoResponse;
import com.shapebox.demo.service.ProdutoService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<Long> criarProduto(@Valid @RequestBody ProdutoRequest produtoRequest){
        return ResponseEntity.ok(service.criarProduto(produtoRequest));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProdutoResponse> buscarProdutosBySku(@Valid @PathVariable String sku){
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

    @PostMapping("/uploadImagens/{produtoId}")
    public ResponseEntity<String> uploadImagens(
            @PathVariable Long produtoId,
            @RequestParam("files") List<MultipartFile> files) {
        try {
            service.uploadImagens(produtoId, files);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar as imagens.");
        }
    }

    @GetMapping("/imagens/{produtoId}")
    public ResponseEntity<List<String>> getImagens(@PathVariable Long produtoId) {
        List<String> imagensBase64 = service.getImagensBase64(produtoId);
        return new ResponseEntity<>(imagensBase64, HttpStatus.OK);
    }
}
