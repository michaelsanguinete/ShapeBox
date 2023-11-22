package com.shapebox.demo.repository;

import com.shapebox.demo.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findByUsuarioId(Long usuarioId);
}
