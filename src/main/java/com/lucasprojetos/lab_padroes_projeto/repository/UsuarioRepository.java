package com.lucasprojetos.lab_padroes_projeto.repository;

import com.lucasprojetos.lab_padroes_projeto.model.usuarios.Usuario;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles  WHERE e.userName= (:userName)")
    public Usuario findByUserName(@Param("userName") String userName);
}
