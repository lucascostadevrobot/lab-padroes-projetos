package com.lucasprojetos.lab_padroes_projeto.repository;

import com.lucasprojetos.lab_padroes_projeto.model.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
