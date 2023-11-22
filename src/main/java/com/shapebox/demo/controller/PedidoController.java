package com.shapebox.demo.controller;

import com.shapebox.demo.dto.PedidoRequest;
import com.shapebox.demo.dto.PedidoResponse;
import com.shapebox.demo.service.PedidoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity criarPedido(@Valid @RequestBody PedidoRequest pedidoRequest){
        pedidoService.criarPedido(pedidoRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<PedidoResponse>> buscarPedidosByUsuarioId(@Valid @PathVariable Long usuarioId){
        return ResponseEntity.ok(pedidoService.buscarPedidosByUsuarioId(usuarioId));
    }

    @PutMapping("/cancelar/{pedidoId}")
    public ResponseEntity cancelarPedido(@Valid @PathVariable Long pedidoId){
        pedidoService.cancelarPedido(pedidoId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
