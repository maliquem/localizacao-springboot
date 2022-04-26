package io.github.maliquem.localizacao.domain.repository;

import io.github.maliquem.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    // PESQUISA PELO NOME COMPLETO
    List<Cidade> findByName(String name);
    // PESQUISA PELO NOME LIKE
    @Query(" SELECT c FROM Cidade c WHERE upper(c.nome) LIKE upper(:name) ")
    List<Cidade> findByNameLike(String name);
    // PESQUISA PELO COMEÇO DO NOME
    List<Cidade> findByNameStartsWith(String name);
    // PESQUISA PELO FINAL DO NOME
    List<Cidade> findByNameEndsWith(String name);
    // PESQUISA POR QUALQUER PARTE DO NOME
    List<Cidade> findByNameContains(String name);
    // PESQUISA PELO NÚMERO DE HABITANTES
    List<Cidade> findByHabitantes(Long habitantes);
}
