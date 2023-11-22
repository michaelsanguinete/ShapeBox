package com.shapebox.demo.service;

import com.shapebox.demo.dto.PedidoRequest;
import com.shapebox.demo.dto.PedidoResponse;
import com.shapebox.demo.entity.Pedido;
import com.shapebox.demo.entity.Produto;
import com.shapebox.demo.entity.enums.StatusPedido;
import com.shapebox.demo.repository.PedidoRepository;
import com.shapebox.demo.repository.ProdutoRepository;
import com.shapebox.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ModelMapper mapper;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public void criarPedido(PedidoRequest pedidoRequest) {
        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);
        pedido.setUsuario(usuarioRepository.findById(pedidoRequest.getUsuarioId()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + pedidoRequest.getUsuarioId())));
        pedido.setProdutos(setaListaProdutos(pedidoRequest.getProdutos()));
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setId(null);
        repository.save(pedido);
    }

    public List<Produto> setaListaProdutos(List<Integer> produtos) {
        List<Produto> listaProdutos = new ArrayList<>();
        for (Integer produtoId : produtos) {
            listaProdutos.add(produtoRepository.findById(produtoId.longValue()).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + produtoId)));
        }
        return listaProdutos;
    }

    public List<PedidoResponse> buscarPedidosByUsuarioId(Long usuarioId) {
        List<Pedido> pedidos = repository.findByUsuarioId(usuarioId);
        return pedidos.stream()
                .map(pedido -> mapper.map(pedido, PedidoResponse.class))
                .toList();
    }

    public void cancelarPedido(Long pedidoId) {
        Pedido pedido = repository.findById(pedidoId).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + pedidoId));
        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.setDataFim(LocalDateTime.now());
        repository.save(pedido);
    }
}
