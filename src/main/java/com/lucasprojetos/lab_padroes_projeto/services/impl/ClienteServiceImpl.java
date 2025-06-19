package com.lucasprojetos.lab_padroes_projeto.services.impl;

import com.lucasprojetos.lab_padroes_projeto.model.cliente.Cliente;
import com.lucasprojetos.lab_padroes_projeto.services.ClienteService;
import feign.Client;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Override
    public Iterable<Cliente> buscarTodos() {
        return null;
    }

    @Override
    public void inserir(Cliente clinte) {

    }

    @Override
    public Cliente buscarPorId(Long id) {
        return null;
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
    }

    @Override
    public void deletar(Long id) {

    }
}
