package com.lucasprojetos.lab_padroes_projeto.services;

import com.lucasprojetos.lab_padroes_projeto.model.endereco.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ConsultaViaCepService {
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
