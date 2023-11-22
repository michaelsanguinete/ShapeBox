package com.shapebox.demo.repository;

import com.shapebox.demo.entity.Produto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findBySku(String sku);

    @Modifying
    @Transactional
    @Query("update Produto p set p.quantidadeEstoque = ?1 where p.sku = ?2")
    public void editarQuantidadeEstoqueBySku(int quantidadeEstoque, String sku);
}
