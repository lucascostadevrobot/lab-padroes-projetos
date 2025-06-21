package com.lucasprojetos.lab_padroes_projeto.services;

import com.lucasprojetos.lab_padroes_projeto.model.cliente.Cliente;
import org.springframework.stereotype.Service;


@Service
public interface ClienteService {

    void inserir(Cliente cliente);

    Cliente buscarPorId(Long id);

    Iterable<Cliente> buscarTodos();

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}
