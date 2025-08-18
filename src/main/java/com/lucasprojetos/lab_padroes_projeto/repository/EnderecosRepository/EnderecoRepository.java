package com.lucasprojetos.lab_padroes_projeto.repository.EnderecosRepository;

import com.lucasprojetos.lab_padroes_projeto.model.enderecos.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {

}
