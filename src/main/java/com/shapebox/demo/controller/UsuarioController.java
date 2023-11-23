package com.shapebox.demo.controller;

import com.shapebox.demo.dto.LoginRequest;
import com.shapebox.demo.dto.UsuarioRequest;
import com.shapebox.demo.dto.UsuarioResponse;
import com.shapebox.demo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity criarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        service.criarUsuario(usuarioRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@Valid @PathVariable Long id){
        return ResponseEntity.ok(service.buscarUsuario(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorCpf(@Valid @PathVariable String cpf){
        return ResponseEntity.ok(service.buscarUsuarioPorCpf(cpf));
    }

    @PutMapping("/atualiza/{id}")
    public ResponseEntity atualizarUsuario(@Valid @PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest){
        service.atualizarUsuario(id, usuarioRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @PostMapping("/resetar-senha")
    public ResponseEntity resetarSenha(@Valid @RequestBody LoginRequest loginRequest){
        service.resetarSenha(loginRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
