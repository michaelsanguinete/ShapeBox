package com.shapebox.demo.service;

import com.shapebox.demo.dto.UsuarioRequest;
import com.shapebox.demo.dto.UsuarioResponse;
import com.shapebox.demo.entity.Usuario;
import com.shapebox.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ModelMapper mapper;

    public void criarUsuario(UsuarioRequest usuarioRequest){
        if (repository.findByCpf(usuarioRequest.getCpf()) != null){
            throw new DataIntegrityViolationException("Já existe um usuário cadastrado com o CPF: " + usuarioRequest.getCpf());
        }
        if (repository.findByEmail(usuarioRequest.getEmail()) != null){
            throw new DataIntegrityViolationException("Já existe um usuário cadastrado com o E-mail: " + usuarioRequest.getEmail());
        }
        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
        repository.save(usuario);
    }

    public UsuarioResponse buscarUsuario(Long id){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
        return mapper.map(usuario, UsuarioResponse.class);
    }

    public UsuarioResponse buscarUsuarioPorCpf(String cpf){
        Usuario usuario = repository.findByCpf(cpf);
        if (usuario == null){
            throw new EntityNotFoundException("Usuário não encontrado com o CPF: " + cpf);
        }
        return mapper.map(usuario, UsuarioResponse.class);
    }

    public void atualizarUsuario(Long id, UsuarioRequest usuarioRequest){
        Usuario usuario = repository.findById(id).orElseThrow();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        repository.save(usuario);
    }
}
