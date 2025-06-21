package com.lucasprojetos.lab_padroes_projeto.controller;

import com.lucasprojetos.lab_padroes_projeto.model.cliente.Cliente;
import com.lucasprojetos.lab_padroes_projeto.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/salvarClientes")
    public ResponseEntity<Cliente> saveAll(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/listarClientes")
    public ResponseEntity<Iterable<Cliente>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarTodos());
    }

    @GetMapping("/listarClientes/{id}")
    public ResponseEntity<Cliente> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }

    @PutMapping("/atualizarClientes/{id}")
    public ResponseEntity<Cliente> putById(@PathVariable Long id, Cliente cliente){
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/deletarClientes/{id}")
    public ResponseEntity<Cliente> deleteById(@PathVariable  Long id, Cliente cliente){
        clienteService.deletar(id);
        return ResponseEntity.ok(cliente);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Cliente> listaPorId(@PathVariable Long id) {
        Optional<Cliente> idEncontrado = clienteRepository.findById(id);
        if (idEncontrado.isPresent()) {
            return ResponseEntity.ok(clienteService.buscarPorId(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }*/
}
