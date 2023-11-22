package com.shapebox.demo.service;

import com.shapebox.demo.dto.ProdutoRequest;
import com.shapebox.demo.dto.ProdutoResponse;
import com.shapebox.demo.entity.Produto;
import com.shapebox.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ModelMapper mapper;

    public void criarProduto(ProdutoRequest produtoRequest){
        Produto produto = mapper.map(produtoRequest, Produto.class);
        repository.save(produto);
    }

    public List<ProdutoResponse> buscarProdutosBySku(String sku){
        List<Produto> produtos = repository.findBySku(sku);
        if (produtos.isEmpty()){
            throw new EntityNotFoundException("Nenhum produto encontrado com o sku: " + sku + ".");
        }
        return produtos.stream()
                .map(produto -> mapper.map(produto, ProdutoResponse.class))
                .toList();
    }

    public ProdutoResponse buscarProdutoById(Long id){
        Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado com o id: " + id + "."));
        return mapper.map(produto, ProdutoResponse.class);
    }

    public void atualizarQuantidadeEstoqueBySku(int quantidadeEstoque, String sku){
        repository.editarQuantidadeEstoqueBySku(quantidadeEstoque, sku);
    }
}
