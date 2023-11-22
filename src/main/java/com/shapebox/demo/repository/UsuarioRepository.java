package com.shapebox.demo.repository;

import com.shapebox.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByCpf(String cpf);
    public Usuario findByEmail(String email);
}
