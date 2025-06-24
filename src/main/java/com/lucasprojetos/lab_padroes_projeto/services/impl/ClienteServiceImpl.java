package com.lucasprojetos.lab_padroes_projeto.services.impl;

import com.lucasprojetos.lab_padroes_projeto.model.Endereco.Endereco;
import com.lucasprojetos.lab_padroes_projeto.model.cliente.Cliente;
import com.lucasprojetos.lab_padroes_projeto.repository.ClienteRepository;
import com.lucasprojetos.lab_padroes_projeto.repository.EnderecoRepository;
import com.lucasprojetos.lab_padroes_projeto.services.ClienteService;
import com.lucasprojetos.lab_padroes_projeto.services.ConsultaViaCepService;
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
    private ConsultaViaCepService consultaViaCepService;


    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        return salvarObjetoCliente(cliente);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> idCliente = clienteRepository.findById(id);
        if (idCliente.isPresent()) {
            return clienteRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Optional<Cliente> idEncontrado = clienteRepository.findById(id);
        if (idEncontrado.isPresent()) {
            return salvarObjetoCliente(cliente);
        }
        return null;
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
     *
     * @return
     */
    public Cliente salvarObjetoCliente(Cliente cliente) {
        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            throw new IllegalArgumentException("Endereço ou CEP não informado");
        }

        String cep =  cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(Long.valueOf(cep)).orElseGet(() -> {
            //caso nao existe, integrar com o ViaCep e persistir o retorno
            Endereco novoEndereco = consultaViaCepService.consultarCep((cep));
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }
}
