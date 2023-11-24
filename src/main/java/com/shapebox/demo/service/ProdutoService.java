package com.shapebox.demo.service;

import com.shapebox.demo.dto.ProdutoRequest;
import com.shapebox.demo.dto.ProdutoResponse;
import com.shapebox.demo.entity.Imagem;
import com.shapebox.demo.entity.Produto;
import com.shapebox.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ModelMapper mapper;

    public long criarProduto(ProdutoRequest produtoRequest){
        if (repository.findBySku(produtoRequest.getSku()) != null){
            throw new DataIntegrityViolationException("Já existe um produto com o sku: " + produtoRequest.getSku() + ".");
        }
        Produto produto = mapper.map(produtoRequest, Produto.class);
        repository.save(produto);
        return produto.getId();
    }

    public ProdutoResponse buscarProdutosBySku(String sku){
        Produto produto = repository.findBySku(sku);
        if (produto == null){
            throw new EntityNotFoundException("Nenhum produto encontrado com o sku: " + sku + ".");
        }
        return mapper.map(produto, ProdutoResponse.class);
    }

    public ProdutoResponse buscarProdutoById(Long id){
        Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado com o id: " + id + "."));
        return mapper.map(produto, ProdutoResponse.class);
    }

    public void atualizarQuantidadeEstoqueBySku(int quantidadeEstoque, String sku){
        repository.editarQuantidadeEstoqueBySku(quantidadeEstoque, sku);
    }

    public void uploadImagens(Long produtoId, List<MultipartFile> files) throws IOException {
        Produto produto = repository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        List<Imagem> imagens = new ArrayList<>();
        for (MultipartFile file : files) {
            Imagem imagem = new Imagem();
            imagem.setConteudo(file.getBytes());
            imagem.setProduto(produto);
            imagens.add(imagem);
        }

        produto.setImagens(imagens);
        repository.save(produto);
    }

    public List<String> getImagensBase64(Long produtoId) {
        Produto produto = repository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return produto.getImagens()
                .stream()
                .map(imagem -> Base64.getEncoder().encodeToString(imagem.getConteudo()))
                .collect(Collectors.toList());
    }
}
