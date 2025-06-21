package com.lucasprojetos.lab_padroes_projeto.services.impl;

import com.lucasprojetos.lab_padroes_projeto.model.Endereco.Endereco;
import com.lucasprojetos.lab_padroes_projeto.model.cliente.Cliente;
import com.lucasprojetos.lab_padroes_projeto.repository.ClienteRepository;
import com.lucasprojetos.lab_padroes_projeto.repository.EnderecoRepository;
import com.lucasprojetos.lab_padroes_projeto.services.ClienteService;
import com.lucasprojetos.lab_padroes_projeto.services.ConsultaViaCepService;
import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ConsultaViaCepService consultaViaCepService;


    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarObjetoCliente(null, cliente);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> idCliente = clienteRepository.findById(id);
        if (idCliente.isPresent()) {
            return clienteService.buscarPorId(id);
        }
        return null;
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        salvarObjetoCliente(id, cliente);
    }

    @Override
    public void deletar(Long id) {
        Optional<Cliente> idCliente = clienteRepository.findById(id);
        if (idCliente.isPresent()) {
            clienteRepository.deleteById(id);
        }
    }

    /**
     * Metodo compacto para persistir o cliente de forma completada incluindo:
     * Verifica se endereço existe, senao salva um novo endereco consultando ViaCep
     * Verifica se o IdCliente esta presente, se sim pega o cliente em questão.
     */
    private void salvarObjetoCliente(Long id, Cliente cliente) {
        Optional<Cliente> idClienteEscolhido = clienteRepository.findById(id);
        Long cep = cliente.getEndereco().getCep();
        if (idClienteEscolhido.isPresent()) {
            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                //caso nao existe, integrar com o ViaCep e persistir o retorno
                Endereco novoEndereco = consultaViaCepService.consultarCep(String.valueOf(cep));
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);
        }
    }
}
